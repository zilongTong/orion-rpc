package org.orion.common;

import lombok.Data;

import java.lang.reflect.Method;

/**
 * @ClassName HttpRequest
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 16:07
 **/
@Data
public class HttpRequest {
    private String requestId;
    private String serverId;
    private Method method;
    private RequestMethod requestMethod;
    private String mappingUrl;
    private Class<?>[] parameterTypes;
    private Object[] parameters;
    private RoutingStrategy routingStrategy;
}
