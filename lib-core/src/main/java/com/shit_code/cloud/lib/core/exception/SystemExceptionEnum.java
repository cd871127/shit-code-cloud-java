package com.shit_code.cloud.lib.core.exception;

/**
 * @author Anthony Chen
 * @date 2020/2/20
 **/
public enum SystemExceptionEnum implements ExceptionInfo {
    /**
     * 系统异常1开头
     */
    UNKNOWN_ERROR("100000", "未知错误");

    private String code;
    private String msg;

    SystemExceptionEnum(String code, String msg) {

        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getMsg() {
        return this.msg;
    }
}
