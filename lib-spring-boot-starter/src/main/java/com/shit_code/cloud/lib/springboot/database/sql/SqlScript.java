package com.shit_code.cloud.lib.springboot.database.sql;

import com.shit_code.cloud.lib.springboot.database.sharding.ShardingInfo;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/2/25
 **/
@Data
public class SqlScript  {
    /**
     * 脚本名称
     */
    private String name;
    /**
     * 脚本版本
     */
    private String version;
    /**
     * 脚本原始内容
     */
    private String originContent;
    /**
     * 生成的脚本
     */
    private List<String> generatedContents;

    /**
     * 分库分表信息
     */
    private ShardingInfo sharding;

}
