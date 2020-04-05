package com.shit_code.cloud.gateway.service;

import com.shit_code.cloud.gateway.GatewayApplication;
import com.shit_code.cloud.gateway.dao.dto.RouteDTO;
import com.shit_code.cloud.gateway.dao.mapper.RouteMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {GatewayApplication.class})
public class RouteServiceTest {

    @Resource
    private RouteService routeService;
    @Resource
    private RouteMapper routeMapper;

    @Test
    public void test() {
//        String definition="RouteDefinition{id='ReactiveCompositeDiscoveryClient_service-admin', predicates=[PredicateDefinition{name='Path', args={pattern=/service-admin/**}}], filters=[FilterDefinition{name='RewritePath', args={regexp=/service-admin/(?<remaining>.*), replacement=/${remaining}}}], uri=lb://service-admin, order=0, metadata={}}";
        String definition = "route001=http://127.0.0.1,Host=**.addrequestparameter.org,Path=/get";
        RouteDefinition routeDefinition = new RouteDefinition(definition);

        routeService.add(routeDefinition);

    }

    @Test
    public void test2() {
        RouteDTO test = routeMapper.selectOne(null, null, null);
        System.out.println(test);
    }
}