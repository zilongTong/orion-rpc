package org.orion.common;

import com.zhangmen.common.annotation.EnableZmDiscoveryClient;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.cloud.commons.util.SpringFactoryImportSelector;

/**
 * @ClassName EnableZmDiscoveryImportSelector
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/29 14:14
 **/
//@Order(2147483547)
public class EnableZmDiscoveryImportSelector extends SpringFactoryImportSelector<EnableZmDiscoveryClient> {
    public EnableZmDiscoveryImportSelector() {

    }

    protected boolean isEnabled() {
        return (Boolean) (new RelaxedPropertyResolver(this.getEnvironment())).getProperty("spring.cloud.zm.discovery.enabled", Boolean.class, Boolean.TRUE);
    }
}
