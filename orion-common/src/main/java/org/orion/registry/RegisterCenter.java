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

    public static void register(VMState state, String weight, String serviceAddress) {
        log.info("RegisterCenter---" + state.name() + "/" + serviceAddress);
        String servicePath = ZKconfig.ZK_REGISTER_PATH + "/" + state.name();
        String addressPath = servicePath + "/" + serviceAddress;
        try {
            if (zkClient.checkPathIsNull(servicePath)) {
                zkClient.writeNode(servicePath, "0", CreateMode.PERSISTENT, false);
            }
            zkClient.writeNode(addressPath, weight, CreateMode.EPHEMERAL, true);
        } catch (Exception e) {
            log.error("RegisterCenter failed {}", state.name() + "/" + serviceAddress, e);
        }
    }

    public static boolean idleTransferToFull(String weight) {
        String fullPath = ZKconfig.ZK_REGISTER_PATH + "/" + VMState.FULL.name() + "/" + IPUtils.getIpAddress();
        String idlePath = ZKconfig.ZK_REGISTER_PATH + "/" + VMState.IDLE.name() + "/" + IPUtils.getIpAddress();
        if (zkClient.checkPathIsNull(fullPath)) {
            if (!zkClient.checkPathIsNull(idlePath)) {
                zkClient.deleteNode(idlePath);
            }
            try {
                String response = zkClient.writeNode(fullPath, weight, CreateMode.EPHEMERAL, true);
                return !StringUtils.isEmpty(response);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return false;
            }
        }
        return true;
    }

    public static boolean fullTransferToIdle(String weight) {
        String fullPath = ZKconfig.ZK_REGISTER_PATH + "/" + VMState.FULL.name() + "/" + IPUtils.getIpAddress();
        String idlePath = ZKconfig.ZK_REGISTER_PATH + "/" + VMState.IDLE.name() + "/" + IPUtils.getIpAddress();
        if (zkClient.checkPathIsNull(idlePath)) {
            if (!zkClient.checkPathIsNull(fullPath)) {
                zkClient.deleteNode(fullPath);
            }
            try {
                String response = zkClient.writeNode(idlePath, weight, CreateMode.EPHEMERAL, true);
                return !StringUtils.isEmpty(response);
            } catch (Exception e) {
                log.error(e.getMessage(), e);
                return false;
            }
        }
        return true;
    }

    public static boolean isFull() {
        String fullPath = ZKconfig.ZK_REGISTER_PATH + "/" + VMState.FULL.name() + "/" + IPUtils.getIpAddress();
        return !zkClient.checkPathIsNull(fullPath);
    }

    public static void main(String[] args) {

        RegisterCenter.register(VMState.IDLE, "11", "12.1.2.3");
        RegisterCenter.register(VMState.IDLE, "22", "12.1.2.31");
        RegisterCenter.register(VMState.IDLE, "33", "12.1.2.3");
        RegisterCenter.register(VMState.IDLE, "33", "12.1.2.3");
        RegisterCenter.register(VMState.IDLE, "33", "12.1.2.3");
        RegisterCenter.register(VMState.IDLE, "121", "12.1.2.32");
        RegisterCenter.register(VMState.FULL, "141", "12.1.2.33");
        RegisterCenter.register(VMState.COLD, "2", "12.1.2.35");
        try {
            Thread.sleep(1000000000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
