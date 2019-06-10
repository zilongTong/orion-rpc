package org.orion.endpoint;

import org.orion.common.annotation.EnableZmDiscoveryClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName ServerEndpoint
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/6/10 11:23
 **/
@RestController
public class ServerEndpoint {
    @RequestMapping(value = "/hello", method = RequestMethod.POST)
    public String sayHello(@RequestBody String param) {
        return "Hello，" + param;
    }
}