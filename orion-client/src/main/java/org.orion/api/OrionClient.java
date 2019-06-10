package org.orion.api;

import org.orion.common.RequestMethod;
import org.orion.common.annotation.OrionRemoteClient;
import org.orion.common.annotation.OrionRequestBody;
import org.orion.common.annotation.OrionRequestRouter;

/**
 * @ClassName OrionClient
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/6/10 11:29
 **/
@OrionRemoteClient(serverId = "orion-server", value = "orionClient")
public interface OrionClient {

    /**
     * 如果没有指定 @OrionTarget注解，必须自己实现负载策略 balanceStrategy
     * 两种负载方式二选一
     * 如果都不指定，走默认的负载均衡策略
     *
     * @return
     */
    @OrionRequestRouter(method = RequestMethod.POST, value = "/hello/")
    String record(@OrionRequestBody String param);
}
