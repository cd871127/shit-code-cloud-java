package com.shit_code.cloud.gateway.dao.mapper;

import com.shit_code.cloud.gateway.dao.dto.RouteDTO;
import com.shit_code.cloud.lib.springboot.database.mybatis.BaseDTO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RouteMapper {

    int insert(RouteDTO routeDTO);

    int updateById(RouteDTO routeDTO);

    RouteDTO selectOne(@Param("id") String id, @Param("status") BaseDTO.Status status, @Param("uniqueId") String uniqueId);

    List<RouteDTO> selectList(@Param("status") BaseDTO.Status status);

}
