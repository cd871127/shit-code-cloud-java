package com.shit_code.cloud.gateway;

import com.shit_code.cloud.gateway.dao.dto.RouteDTO;
import com.shit_code.cloud.gateway.dao.mapper.RouteMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = {GatewayApplication.class})
public class GatewayApplicationTest {

    @Resource
    private RouteMapper routeMapper;

    @Test
    public void test() {
        RouteDTO routeDTO = new RouteDTO();
        routeDTO.setId("1234525");
        routeDTO.setMetadata("123");
        routeDTO.setOrder(1);
        routeDTO.setUri("ddd");

        routeMapper.select();
    }
}