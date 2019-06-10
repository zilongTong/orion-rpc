package org.orion.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName OrionTarget
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/25 14:47
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface OrionTarget {
}
