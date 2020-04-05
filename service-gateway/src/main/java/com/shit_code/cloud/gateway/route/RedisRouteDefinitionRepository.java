package com.shit_code.cloud.gateway.route;

import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * @author Anthony Chen
 * @date 2020/4/5
 **/
public class RedisRouteDefinitionRepository implements RouteDefinitionRepository {

    private InMemoryRouteDefinitionRepository inMemoryRouteDefinitionRepository = new InMemoryRouteDefinitionRepository();

    @Override
    public Flux<RouteDefinition> getRouteDefinitions() {
        return inMemoryRouteDefinitionRepository.getRouteDefinitions();
    }

    @Override
    public Mono<Void> save(Mono<RouteDefinition> route) {
        return inMemoryRouteDefinitionRepository.save(route);
    }

    @Override
    public Mono<Void> delete(Mono<String> routeId) {
        return inMemoryRouteDefinitionRepository.delete(routeId);
    }
}
