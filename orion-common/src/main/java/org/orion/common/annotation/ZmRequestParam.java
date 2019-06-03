package org.orion.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName ZmRequestParam
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/29 16:38
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ZmRequestParam {
}
