package org.orion.okhttp;


import org.orion.loadbalance.ILoadBalanceStrategy;

/**
 * @ClassName IExecutor
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 11:14
 **/
public interface IExecutor<T> {

    Object execute(T var, ILoadBalanceStrategy strategy);
}
