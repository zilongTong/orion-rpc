package org.orion.common.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * @ClassName OrionRequestBody
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/25 14:48
 **/
@Target({ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Component
public @interface OrionRequestBody {
}
