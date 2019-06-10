package org.orion.endpoint;

import org.orion.api.OrionClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @ClassName ClientEndPoint
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/6/10 10:46
 **/
@RestController
public class ClientEndPoint {
    @Autowired
    private OrionClient client;

    @RequestMapping(value = "/book", method = RequestMethod.GET)
    @ResponseBody
    public String sayHello(String param) {
        String result = client.record(param);
        System.out.println(result);
        return result;
    }

}
