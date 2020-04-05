package com.shit_code.cloud.gateway.dao.mapper;

import com.shit_code.cloud.gateway.dao.dto.RouteDTO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RouteMapper {
//
//    int insert();
//
//    int update();
//
//    int delete();

    RouteDTO select();

}
