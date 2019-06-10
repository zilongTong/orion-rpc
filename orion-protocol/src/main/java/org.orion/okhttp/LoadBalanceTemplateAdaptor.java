package org.orion.okhttp;


import org.orion.loadbalance.ILoadBalanceStrategy;

/**
 * @ClassName LoadBalanceTemplateAdaptor
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 16:46
 **/
public abstract class LoadBalanceTemplateAdaptor<T> implements IExecutor<T> {
    public Object execute(T t, ILoadBalanceStrategy strategy) {
        return null;
    }

}
