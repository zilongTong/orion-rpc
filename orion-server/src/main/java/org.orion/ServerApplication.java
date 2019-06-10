package org.orion;

import com.spring4all.swagger.EnableSwagger2Doc;
import org.orion.registry.EnableOrionClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @ClassName ServerApplication
 * @Author Leo
 * @Description //TODO
 * @Date: 2019/6/10 11:23
 **/
@EnableSwagger2Doc
@SpringBootApplication
@EnableOrionClient
public class ServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

}
