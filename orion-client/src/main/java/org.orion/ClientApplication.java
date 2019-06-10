package org.orion;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.orion.annotation.EnableOrionDiscovery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ClientApplication
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/6/10 10:47
 **/
@EnableSwagger2Doc
@EnableOrionDiscovery
@SpringBootApplication
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}
