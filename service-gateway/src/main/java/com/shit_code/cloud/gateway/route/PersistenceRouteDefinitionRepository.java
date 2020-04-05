package com.shit_code.cloud.gateway.route;

import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * @author Anthony Chen
 * @date 2020/4/5
 **/
@Component
public class PersistenceRouteDefinitionRepository implements RouteDefinitionRepository {

    private InMemoryRouteDefinitionRepository inMemoryRouteDefinitionRepository;

    private final static String ROUTE_KEY = "com.shit_code.cloud.gateway.routes";

    @Resource
    private RedisTemplate<String, RouteDefinition> redisTemplate;

//    @Resource
//    private RouteMapper routeMapper;

    private BoundHashOperations<String, String, RouteDefinition> boundHashOperations;

    @PostConstruct
    private void init() {
        this.inMemoryRouteDefinitionRepository = new InMemoryRouteDefinitionRepository();
        this.boundHashOperations = redisTemplate.boundHashOps(ROUTE_KEY);
    }

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return Flux.empty();
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        inMemoryRouteDefinitionRepository.save(route);

        return route.flatMap(routeDefinition -> {
            boundHashOperations.put(routeDefinition.getId(), routeDefinition);
            return Mono.empty();
        });
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return Mono.empty();
    }
}
