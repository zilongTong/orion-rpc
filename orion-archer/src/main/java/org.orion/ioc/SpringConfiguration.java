package org.orion.ioc;//package com.zhangmen.orion.ioc;
//
//import com.zhangmen.orion.proxy.RemoteProxy;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.boot.context.properties.EnableConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
///**
// * @ClassName SpringConfiguration
// * @Author Leo
// * @Description //TODO
// * @Date: 2019/5/23 10:37
// **/
//@Configuration
//@EnableConfigurationProperties
////@ConditionalOnClass({EurekaClientConfig.class})
//@ConditionalOnProperty(
//        value = {"discovery.client.enabled"},
//        matchIfMissing = true
//)
//public class SpringConfiguration {
//
//    @Bean
//    public BeanRegistry customBeanDefinitionRegistry() {
//        return new BeanRegistry();
//    }
//
//    @Bean
//    public RemoteProxy remoteProxy() {
//        return new RemoteProxy();
//    }
//
//}