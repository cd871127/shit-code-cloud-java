package com.shit_code.cloud.shiro.configuration;

import org.apache.shiro.mgt.DefaultSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Anthony Chen
 * @date 2020/2/19
 **/
@Configuration
public class ShiroConfiguration {

    @Bean
    public DefaultSecurityManager defaultSecurityManager() {
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
//        defaultSecurityManager.setRealm(new DatabaseRealm());
        return defaultSecurityManager;
    }
}
