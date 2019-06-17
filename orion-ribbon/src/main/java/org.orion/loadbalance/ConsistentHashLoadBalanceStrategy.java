package org.orion.loadbalance;

import org.springframework.beans.factory.InitializingBean;

/**
 * @ClassName ConsistentHashLoadBalanceStrategy
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/6/17 17:50
 **/
public class ConsistentHashLoadBalanceStrategy implements ILoadBalanceStrategy, InitializingBean {


    @Override
    public String loadBalance(String serverName) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}
