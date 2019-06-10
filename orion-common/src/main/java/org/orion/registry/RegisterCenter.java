package org.orion.registry;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.zookeeper.CreateMode;
import org.orion.common.VMState;
import org.orion.common.ZKconfig;
import org.orion.curator.CuratorClient;
import org.orion.utils.IPUtils;


/**
 * @ClassName RegisterCenter
 * @Author Leo
 * @DescrIPUtils.getIpAddress()tion //TODO
 * @Date: 2019/5/15 19:12
 **/
@Slf4j
public class RegisterCenter {

    private static CuratorClient zkClient = CuratorClient.getInstance();

    public static void register(String serverName, String weight, String serviceAddress) {
        log.info("RegisterCenter---" + serverName + "/" + serviceAddress);
        String servicePath = ZKconfig.ZK_REGISTER_PATH + "/" + serverName;
        String addressPath = servicePath + "/" + serviceAddress;
        try {
            if (zkClient.checkPathIsNull(servicePath)) {
                zkClient.writeNode(servicePath, "0", CreateMode.PERSISTENT, false);
            }
            zkClient.writeNode(addressPath, weight, CreateMode.EPHEMERAL, true);
        } catch (Exception e) {
            log.error("RegisterCenter failed {}", serverName + "/" + serviceAddress, e);
        }
    }
}
