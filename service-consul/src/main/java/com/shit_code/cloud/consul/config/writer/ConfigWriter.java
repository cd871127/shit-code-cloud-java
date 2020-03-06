package com.shit_code.cloud.consul.config.writer;

import com.ecwid.consul.v1.kv.model.GetValue;

import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
public interface ConfigWriter {
    /**
     * 写配置
     *
     * @param values
     * @return 成功的数量
     */
    int write(List<GetValue> values);
}
