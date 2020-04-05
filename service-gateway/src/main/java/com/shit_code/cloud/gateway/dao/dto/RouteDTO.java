package com.shit_code.cloud.gateway.dao.dto;

import com.shit_code.cloud.lib.springboot.database.mybatis.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;


@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class RouteDTO extends BaseDTO {
    private String id;
    private String uri;
    private Integer order;
    private String metadata;
    private List<RouteAccessoryDTO> accessories;
}
