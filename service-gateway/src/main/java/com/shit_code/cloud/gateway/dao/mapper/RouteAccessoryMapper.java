package com.shit_code.cloud.gateway.dao.mapper;

import com.shit_code.cloud.gateway.dao.dto.RouteAccessoryDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RouteAccessoryMapper {

    int selectList(RouteAccessoryDTO routeAccessoryDTO);

    int insert(RouteAccessoryDTO routeAccessoryDTO);

    int insertList(List<RouteAccessoryDTO> routeAccessoryDTO);

}
