package org.orion.proxy;


import lombok.extern.slf4j.Slf4j;
import net.sf.cglib.proxy.InvocationHandler;
import net.sf.cglib.proxy.Proxy;
import org.orion.common.BizException;
import org.orion.common.HttpRequest;
import org.orion.common.RequestMethod;
import org.orion.common.annotation.OrionRemoteClient;
import org.orion.common.annotation.OrionRequestRouter;
import org.orion.loadbalance.ILoadBalanceStrategy;
import org.orion.loadbalance.RandomLoadBalanceStrategy;

import org.orion.okhttp.LoadBalanceTemplateAdaptor;
import org.orion.okhttp.ProtocolEnum;
import org.orion.okhttp.RemoteExecutorFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.util.StringUtils;

import java.lang.reflect.Method;

/**
 * @ClassName RemoteProxy
 * @Author Leo
 * @Description //TODO
 **/
@Slf4j
public class RemoteProxy implements InvocationHandler, ApplicationContextAware {


    private Class<?> interfaceClass;

    private String procotol;

    private static ApplicationContext applicationContext;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public Object bind(Class<?> cls) {
        this.interfaceClass = cls;
        return Proxy.newProxyInstance(cls.getClassLoader(), new Class[]{interfaceClass}, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        HttpRequest request = new HttpRequest();
        OrionRequestRouter requestRouting = method.getAnnotation(OrionRequestRouter.class);
        OrionRemoteClient zmRemoteClient = this.interfaceClass.getAnnotation(OrionRemoteClient.class);
        if (zmRemoteClient != null) {
            request.setServerId(zmRemoteClient.serverId());
        }
        if (requestRouting == null) {
            throw new BizException("no RequestRouter Exception");
        }
        String[] values = requestRouting.value();
        RequestMethod[] requestMethods = requestRouting.method();
        LoadBalanceTemplateAdaptor executor = RemoteExecutorFactory.protocol(ProtocolEnum.HTTP);
        request.setRequestMethod(requestMethods[0]);
        request.setMethod(method);
        request.setParameters(args);
        request.setMappingUrl(values[0]);
        String balanceStrategy = requestRouting.balanceStrategy();
        ILoadBalanceStrategy strategy = null;
        if (StringUtils.isEmpty(balanceStrategy)) {
            strategy = (ILoadBalanceStrategy) applicationContext.getBean(RandomLoadBalanceStrategy.class);
        } else {
            strategy = (ILoadBalanceStrategy) applicationContext.getBean(balanceStrategy);
        }
        return executor.execute(request, strategy);
    }
}
