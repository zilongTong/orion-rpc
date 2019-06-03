package org.orion.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName ZmUrlRequestParam
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/29 16:37
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface ZmUrlRequestParam {
}
