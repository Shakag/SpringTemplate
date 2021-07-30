package com.shakag.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";
    public static final String DEFAULT_ERROR_MESSAGE = "网络异常";
    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    public static final Integer DEFAULT_ERROR_CODE = 500;
    
    private int code;
    private String msg;
    private Object data;

    public static Result success(){
        return new Result(DEFAULT_SUCCESS_CODE,DEFAULT_SUCCESS_MESSAGE,null);
    }

    public static Result success(Object data){
        return new Result(DEFAULT_SUCCESS_CODE,DEFAULT_SUCCESS_MESSAGE,data);
    }
    public static Result success(String msg,Object data){
        return new Result(DEFAULT_SUCCESS_CODE,msg,data);
    }

    public static Result success(int code,String msg,Object data){
        return new Result(code,msg,data);
    }

    public static Result fail(String msg){
        return new Result(DEFAULT_ERROR_CODE,msg,null);
    }

    public static Result fail(int code,String msg){
        return new Result(code,msg,null);
    }
}
