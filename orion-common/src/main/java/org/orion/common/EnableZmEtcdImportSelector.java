package org.orion.common;

import com.zhangmen.common.annotation.EnableRegisterClient;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.cloud.commons.util.SpringFactoryImportSelector;

/**
 * @ClassName EnableZmDiscoveryImportSelector
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/29 14:14
 **/
//@Order(2147483547)
public class EnableZmEtcdImportSelector extends SpringFactoryImportSelector<EnableRegisterClient> {

    public EnableZmEtcdImportSelector() {

    }

    protected boolean isEnabled() {
        return (Boolean) (new RelaxedPropertyResolver(this.getEnvironment())).getProperty("spring.cloud.zm.registry.enabled", Boolean.class, Boolean.TRUE);
    }
}
