package com.shit_code.cloud.lib.springboot.database.sql;

import com.shit_code.cloud.lib.springboot.database.sharding.ShardingInfo;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * 脚本实体
 *
 * @author Anthony Chen
 * @date 2020/2/25
 **/
@Data
public class SqlScript {
    /**
     * 脚本名称
     */
    private String name;
    /**
     * 脚本版本
     */
    private String version;
    /**
     * 脚本模板
     */
    private String template;
    /**
     * 生成的脚本
     */
    private List<String> sqlList;

    /**
     * 时间分表起始日期
     */
    private LocalDate startDate = LocalDate.now();
    /**
     * 时间分表终止
     */
    private LocalDate endDate;

    /**
     * 分库分表信息
     */
    private ShardingInfo sharding = new ShardingInfo();

    public void setStartDate(String date) {
        this.startDate = LocalDate.parse(date);
    }

    public void setEndDate(String date) {
        this.endDate = LocalDate.parse(date);
    }
}
