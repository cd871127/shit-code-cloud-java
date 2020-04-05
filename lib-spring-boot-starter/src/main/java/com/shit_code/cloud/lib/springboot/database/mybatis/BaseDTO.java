package com.shit_code.cloud.lib.springboot.database.mybatis;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class BaseDTO {
    private String uniqueId;
    private Integer version;
    private Status status = Status.INVALID;
    private String createBy;
    private LocalDateTime createTime;
    private String updateBy;
    private LocalDateTime updateTime;

    public enum Status {
        INVALID, VALID, DELETED
    }
}
