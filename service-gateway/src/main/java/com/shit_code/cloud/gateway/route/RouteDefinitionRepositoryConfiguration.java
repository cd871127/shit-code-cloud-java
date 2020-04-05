package com.shit_code.cloud.gateway.route;

import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

@Configuration
public class RouteDefinitionRepositoryConfiguration {

    @Bean
    RouteDefinitionRepository routeDefinitionRepositoryProxy() {
        RedisRouteDefinitionRepository redisRouteDefinitionRepository = new RedisRouteDefinitionRepository();
        DatabaseRouteDefinitionRepository databaseRouteDefinitionRepository = new DatabaseRouteDefinitionRepository();
        return new RouteDefinitionRepositoryProxy(databaseRouteDefinitionRepository, redisRouteDefinitionRepository);
    }
}
