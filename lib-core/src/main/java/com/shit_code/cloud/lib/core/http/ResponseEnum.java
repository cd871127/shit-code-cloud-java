package com.shit_code.cloud.lib.core.http;

/**
 * @author Anthony Chen
 * @date 2020/2/20
 **/
public enum ResponseEnum implements ResponseInfo {
    /**
     * 请求代码0开头
     */
    SUCCESS("000000", "SUCCESS");

    private String code;
    private String msg;

    ResponseEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMsg() {
        return msg;
    }

}
