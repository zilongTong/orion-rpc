package org.orion.protocol;

import lombok.extern.slf4j.Slf4j;
import org.orion.protocol.tcp.TcpExecutor;

/**
 * @ClassName RemoteExecutorFactory
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/22 10:54
 **/
@Slf4j
public class RemoteExecutorFactory {


    public static LoadBalanceTemplateAdaptor protocol(ProtocolEnum protocolEnum) {
        if (protocolEnum.getCode() == 1) {
            return new TcpExecutor();
        } else {
            return new HttpExecutor();
        }
    }
}
