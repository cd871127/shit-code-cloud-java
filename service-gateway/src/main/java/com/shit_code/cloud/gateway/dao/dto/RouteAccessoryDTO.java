package com.shit_code.cloud.gateway.dao.dto;

import com.shit_code.cloud.lib.springboot.database.mybatis.BaseDTO;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
@Data
public class RouteAccessoryDTO extends BaseDTO {
    /**
     * 名字
     */
    private String name;
    /**
     * 参数
     */
    private String args;
    /**
     * 附件类型
     */
    private AccessoryType type;
    /**
     * 路由ID
     */
    private String id;

    public enum AccessoryType {
        /**
         * 断言和过滤器
         */
        PREDICATE, FILTER;
    }
}
