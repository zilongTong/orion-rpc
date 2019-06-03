package org.orion.common;

/**
 * @ClassName RequestMethod
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 15:37
 **/
public enum RequestMethod {
    GET,
    HEAD,
    POST,
    PUT,
    PATCH,
    DELETE,
    OPTIONS,
    TRACE;

    private RequestMethod() {
    }
}
