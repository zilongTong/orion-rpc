package org.orion.common.annotation;

import org.orion.registry.ServerRegister;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableRegisterClient
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/29 10:33
 **/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({ServerRegister.class})
public @interface EnableRegisterClient {
}
