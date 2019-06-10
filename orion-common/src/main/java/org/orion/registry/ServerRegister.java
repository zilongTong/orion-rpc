package org.orion.registry;


import lombok.extern.slf4j.Slf4j;
import org.orion.common.VMState;
import org.orion.utils.IPUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;

/**
 * @ClassName ServerRegister
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/5/30 11:16
 **/
@Slf4j
public class ServerRegister implements InitializingBean {

    @Value("${server.name}")
    private String serverName;

    @Override
    public void afterPropertiesSet() throws Exception {
        RegisterCenter.register(serverName, "0", IPUtils.getIpAddress());
    }
}
