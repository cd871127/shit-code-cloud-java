package com.shit_code.cloud.gateway.service;

import com.shit_code.cloud.gateway.dao.dto.RouteAccessoryDTO;
import com.shit_code.cloud.gateway.dao.dto.RouteDTO;
import com.shit_code.cloud.gateway.dao.mapper.RouteAccessoryMapper;
import com.shit_code.cloud.gateway.dao.mapper.RouteMapper;
import com.shit_code.cloud.lib.springboot.database.mybatis.BaseDTO;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Anthony
 * @date 2020/4/6
 **/
@Service
public class RouteService {
    @Resource
    private RouteMapper routeMapper;

    @Resource
    private RouteAccessoryMapper routeAccessoryMapper;

    @Transactional(rollbackFor = Exception.class)
    public void add(RouteDefinition routeDefinition) {
        RouteDTO routeDTO = RouteDTO.fromDefinition(routeDefinition);
        routeMapper.insert(routeDTO);
        List<RouteAccessoryDTO> accessories = new ArrayList<>(
                routeDTO.getFilters().size() + routeDTO.getPredicates().size());
        accessories.addAll(routeDTO.getFilters());
        accessories.addAll(routeDTO.getPredicates());
        if (CollectionUtils.isNotEmpty(accessories)) {
            routeAccessoryMapper.insertList(accessories);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    public void update(RouteDefinition routeDefinition) {
        RouteDTO routeDTO = RouteDTO.fromDefinition(routeDefinition);
        routeMapper.updateById(routeDTO);


    }

    public void delete(String id) {

    }

    public void updateStatus(String id, BaseDTO.Status status) {

    }

}
