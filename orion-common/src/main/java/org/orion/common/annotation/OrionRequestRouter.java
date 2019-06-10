package org.orion.common.annotation;

import org.orion.common.RequestMethod;
import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName OrionRequestRouter
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 15:36
 **/
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface OrionRequestRouter {
    String name() default "";

    @AliasFor("path")
    String[] value() default {};

    @AliasFor("value")
    String[] path() default {};

    String balanceStrategy() default "";

    RequestMethod[] method() default {};

}
