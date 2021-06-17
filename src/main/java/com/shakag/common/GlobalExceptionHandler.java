package com.shakag.common;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result error(Exception e){
        e.printStackTrace();
        return new Result(400,"Global Exception",null);
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public Result error(IOException e){
        e.printStackTrace();
        return new Result(400,"IOException",null);
    }
}