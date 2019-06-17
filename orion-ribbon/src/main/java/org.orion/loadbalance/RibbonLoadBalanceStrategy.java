package org.orion.loadbalance;

import org.springframework.beans.factory.InitializingBean;

/**
 * @ClassName RibbonLoadBalanceStrategy
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/6/17 18:04
 **/
public class RibbonLoadBalanceStrategy implements ILoadBalanceStrategy, InitializingBean {
    @Override
    public String loadBalance(String serverName) {
        return null;
    }

    @Override
    public void afterPropertiesSet() throws Exception {

    }
}

