package com.shit_code.cloud.shiro.realm;

import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.realm.Realm;

/**
 * @author Anthony Chen
 * @date 2020/2/23
 **/
public interface ShitCodeRealm extends Realm {
    /**
     * 判断是否支持token
     *
     * @param token token
     * @return true 支持,false 不支持
     */
    @Override
    default boolean supports(AuthenticationToken token) {
        return false;
    }
}
