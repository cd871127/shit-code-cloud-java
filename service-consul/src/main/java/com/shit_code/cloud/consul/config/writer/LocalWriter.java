package com.shit_code.cloud.consul.config.writer;

import com.ecwid.consul.v1.kv.model.GetValue;
import com.shit_code.cloud.consul.config.configuration.ConfigProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Anthony Chen
 * @date 2020/3/6
 **/
@Component
public class LocalWriter implements ConfigWriter {

    @Resource
    private ConfigProperties configProperties;

    @Override
    public int write(List<GetValue> values) {

        return 0;
    }
}
