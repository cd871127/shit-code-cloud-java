package com.shit_code.cloud.lib.core.http;

/**
 * @author Anthony Chen
 * @date 2020/2/20
 **/
public interface ResponseInfo {
    /**
     * 返回异常代码
     *
     * @return 异常代码
     */
    String getCode();

    /**
     * 返回异常描述
     *
     * @return 异常描述
     */
    String getMsg();
}
