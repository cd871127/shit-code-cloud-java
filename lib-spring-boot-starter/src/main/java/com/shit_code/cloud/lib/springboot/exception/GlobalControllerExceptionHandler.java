package com.shit_code.cloud.lib.springboot.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author Anthony Chen
 * @date 2020/2/20
 **/
@RestControllerAdvice
public class GlobalControllerExceptionHandler {
    @ExceptionHandler(Exception.class)
    public String handle() {
        return null;
    }

}
