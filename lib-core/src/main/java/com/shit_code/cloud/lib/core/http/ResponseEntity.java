package com.shit_code.cloud.lib.core.http;

import lombok.Data;

/**
 * @author Anthony Chen
 * @date 2020/2/20
 **/
@Data
public class ResponseEntity<T> {
    private ResponseInfo responseInfo;
    private T data;

    public String getCode() {
        return responseInfo.getCode();
    }


    public String getMsg() {
        return responseInfo.getMsg();
    }


}
