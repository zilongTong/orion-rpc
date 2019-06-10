package org.orion.ioc;


import lombok.extern.slf4j.Slf4j;
import org.orion.common.annotation.OrionRemoteClient;
import org.orion.proxy.ProxyFactory;
import org.orion.utils.ClassUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.beans.factory.support.GenericBeanDefinition;

import java.util.Set;

/**
 * @ClassName BeanRegistry
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/15 17:47
 **/
@Slf4j
public class BeanRegistry implements BeanDefinitionRegistryPostProcessor {

    private static final String ZMLEARM = "org.orion";

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {

    }

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        // 需要被代理的接口
        Set<Class<?>> clazzs = ClassUtil.getClasses(ZMLEARM);
        clazzs.forEach(cls -> {
            OrionRemoteClient client = cls.getAnnotation(OrionRemoteClient.class);
            if (client != null) {
                BeanDefinitionBuilder builder = BeanDefinitionBuilder.genericBeanDefinition(cls);
                GenericBeanDefinition definition = (GenericBeanDefinition) builder.getRawBeanDefinition();
                definition.getPropertyValues().add("interfaceClass", definition.getBeanClassName());
                definition.setBeanClass(ProxyFactory.class);
                definition.setAutowireMode(GenericBeanDefinition.AUTOWIRE_BY_TYPE);
                beanDefinitionRegistry.registerBeanDefinition(client.value(), definition);
            }
        });
    }


}
