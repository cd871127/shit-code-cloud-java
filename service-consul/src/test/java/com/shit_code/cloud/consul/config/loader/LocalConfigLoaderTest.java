package com.shit_code.cloud.consul.config.loader;

import com.shit_code.cloud.consul.config.configuration.Config;
import com.shit_code.cloud.consul.config.configuration.ConfigProperties;
import org.junit.Test;

import java.util.Collections;
import java.util.List;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = {Application.class})
public class LocalConfigLoaderTest {
//    @Resource
//    LocalConfigLoader localConfigLoader;

    @Test
    public void loadConfig() {
        ConfigProperties.LocalConfig localConfig = new ConfigProperties.LocalConfig();
        localConfig.setPath("D:\\dev\\code\\shit-code-cloud-java\\app-config");
        localConfig.setSuffix(Collections.singletonList("yml"));
        ConfigProperties configProperties = new ConfigProperties();
        configProperties.setLocal(localConfig);
        configProperties.setEnv("");
        LocalConfigLoader localConfigLoader = new LocalConfigLoader(configProperties);
        List<Config> configs = localConfigLoader.loadConfig();
        System.out.println(configs.size());
        configProperties.setEnv("dev");
        configs = localConfigLoader.loadConfig();
        System.out.println(configs.size());
//        localConfigLoader.loadConfig();
    }
}