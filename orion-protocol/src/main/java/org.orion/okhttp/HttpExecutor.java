package org.orion.okhttp;

import lombok.extern.slf4j.Slf4j;
import okhttp3.Response;
import org.apache.commons.lang3.StringUtils;
import org.orion.common.BizException;
import org.orion.common.HttpRequest;
import org.orion.common.RequestMethod;
import org.orion.common.annotation.OrionRequestBody;
import org.orion.common.annotation.OrionTarget;
import org.orion.loadbalance.ILoadBalanceStrategy;


import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName HttpExecutor
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 10:55
 **/
@Slf4j
public class HttpExecutor extends LoadBalanceTemplateAdaptor<HttpRequest> {

    /**
     * 远程调用方法
     *
     * @param request
     * @return
     */
    @Override
    public Object execute(HttpRequest request, ILoadBalanceStrategy strategy) {
        Object[] ps = request.getParameters();
        String target = null;
        Object jsonParam = null;
        Method method = request.getMethod();
        Annotation[][] annotations = method.getParameterAnnotations();
        for (int i = 0; i < ps.length; i++) {
            Annotation a = annotations[i][0];
            Object o = ps[i];
            if (a instanceof OrionTarget) {
                target = o.toString();
            }
            if (a instanceof OrionRequestBody) {
                jsonParam = o;
            }
        }
        String mappingUrl = request.getMappingUrl();
        if (StringUtils.isEmpty(mappingUrl)) {
            throw new BizException("no right server mapping url");
        }
        if (target == null && strategy != null) {
            target = strategy.loadBalance(request.getServerId());
        }
        if (StringUtils.isEmpty(target)) {
            throw new BizException("no available server found");
        }
        RequestMethod requestMethod = request.getRequestMethod();
        request.getParameters();
        Response result = null;
        if (requestMethod.name().equalsIgnoreCase(RequestMethod.POST.name())) {
            try {
                result = OkHttpUtil.postJsonParams(String.format("http://%s:8081%s", target, mappingUrl), jsonParam);
            } catch (Exception e) {
                log.error("OkHttpUtil execute POST Exception", e);
            }
        } else if (requestMethod.name().equalsIgnoreCase(RequestMethod.GET.name())) {
            try {
//                CommandContext commandContext = new CommandContext();
//                Response response = OkHttpUtil.client(new OkHttpRequestBuilder(commandContext).toRequest());
                result = OkHttpUtil.get(String.format("http://%s:8081%s", target, mappingUrl), objectToMap(jsonParam));
            } catch (Exception e) {
                log.error("OkHttpUtil execute GET Exception", e);
            }
        } else {
            throw new BizException("RequestMethod support POST GET only");
        }
        try {
            return result.body().string();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Map<String, String> objectToMap(Object obj) throws Exception {
        if (obj == null) {
            return null;
        }
        Map<String, String> map = new HashMap();
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        for (Field field : declaredFields) {
            field.setAccessible(true);
            map.put(field.getName(), field.get(obj).toString());
        }
        return map;
    }
}

