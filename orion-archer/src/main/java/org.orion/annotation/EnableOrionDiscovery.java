package org.orion.annotation;


import org.orion.ioc.BeanRegistry;
import org.orion.loadbalance.RandomLoadBalanceStrategy;
import org.orion.proxy.RemoteProxy;
import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * @ClassName EnableOrionDiscovery
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/29 10:32
 **/

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import({BeanRegistry.class, RemoteProxy.class, RandomLoadBalanceStrategy.class})
public @interface EnableOrionDiscovery {
}
