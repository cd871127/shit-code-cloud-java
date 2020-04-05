package com.shit_code.cloud.lib.springboot.database.mybatis;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {
    private String unique_id;
    private Integer version;
    private Integer valid;
    private String createBy;
    private LocalDateTime createTime;
    private String updateTime;
    private LocalDateTime updateBy;
}
