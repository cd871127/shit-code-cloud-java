package com.shit_code.cloud.gateway.dao.dto;

import com.alibaba.fastjson.JSONObject;
import com.shit_code.cloud.lib.springboot.database.mybatis.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cloud.gateway.route.RouteDefinition;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class RouteDTO extends BaseDTO {
    private String id;
    private String uri;
    private Integer order = Integer.MAX_VALUE;
    private String metadata;
    private List<RouteAccessoryDTO> filters;
    private List<RouteAccessoryDTO> predicates;

    public static RouteDTO fromDefinition(RouteDefinition routeDefinition) {
        RouteDTO routeDTO = new RouteDTO();
        final String id = routeDefinition.getId();
        routeDTO.setId(routeDefinition.getId());
        routeDTO.setUri(routeDefinition.getUri().toString());
        routeDTO.setOrder(routeDefinition.getOrder());
        routeDTO.setMetadata(JSONObject.toJSONString(routeDefinition.getMetadata()));
        if (CollectionUtils.isNotEmpty(routeDefinition.getFilters())) {
            routeDTO.setFilters(routeDefinition.getFilters().parallelStream().map(
                    filterDefinition -> RouteAccessoryDTO.fromDefinition(id, filterDefinition))
                    .collect(Collectors.toList()));
        } else {
            routeDTO.setFilters(Collections.emptyList());
        }

        if (CollectionUtils.isNotEmpty(routeDefinition.getPredicates())) {
            routeDTO.setPredicates(routeDefinition.getPredicates().parallelStream().map(
                    predicateDefinition -> RouteAccessoryDTO.fromDefinition(id, predicateDefinition))
                    .collect(Collectors.toList()));
        } else {
            routeDTO.setPredicates(Collections.emptyList());
        }

        return routeDTO;
    }
}
