package org.orion.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName ZmRpc
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 13:59
 **/
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface OrionRemoteClient {
    String value() default "";

    String serverId() default "";
}
