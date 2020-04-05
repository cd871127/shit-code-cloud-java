package com.shit_code.cloud.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.gateway.route.RouteDefinitionRouteLocator;

@SpringCloudApplication
public class GatewayApplication {
    public static void main(String[] args) {
//        https://www.jianshu.com/p/8f007bcf36ea
        SpringApplication.run(GatewayApplication.class, args);
    }
}
