package com.shit_code.cloud.gateway.route;

import org.springframework.cloud.gateway.route.InMemoryRouteDefinitionRepository;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public class RouteDefinitionRepositoryProxy implements RouteDefinitionRepository {

    private InMemoryRouteDefinitionRepository inMemoryRouteDefinitionRepository;

    private DatabaseRouteDefinitionRepository databaseRouteDefinitionRepository;

    private RedisRouteDefinitionRepository redisRouteDefinitionRepository;

    private RouteDefinitionRepositoryProxy() {
        this.inMemoryRouteDefinitionRepository = new InMemoryRouteDefinitionRepository();
    }

    public RouteDefinitionRepositoryProxy(DatabaseRouteDefinitionRepository databaseRouteDefinitionRepository
            , RedisRouteDefinitionRepository redisRouteDefinitionRepository) {
        this();
        this.databaseRouteDefinitionRepository = databaseRouteDefinitionRepository;
        this.redisRouteDefinitionRepository = redisRouteDefinitionRepository;
    }

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
