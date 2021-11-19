package com.shakag.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_EMPTY)
public class Result<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    public static final String DEFAULT_SUCCESS_MESSAGE = "请求成功";
    public static final String DEFAULT_ERROR_MESSAGE = "网络异常";
    public static final Integer DEFAULT_SUCCESS_CODE = 200;
    public static final Integer DEFAULT_ERROR_CODE = 500;

    @ApiModelProperty("返回状态码")
    private int code;
    @ApiModelProperty("返回提示信息")
    private String msg;
    @ApiModelProperty("返回数据集")
    private T data;

    public static<T> Result<T> success(){
        return new Result<>(DEFAULT_SUCCESS_CODE,DEFAULT_SUCCESS_MESSAGE,null);
    }

    public static<T> Result<T> success(T data){
        return new Result<>(DEFAULT_SUCCESS_CODE,DEFAULT_SUCCESS_MESSAGE,data);
    }

    public static<T> Result<T> success(String msg,T data){
        return new Result<>(DEFAULT_SUCCESS_CODE,msg,data);
    }

    public static<T> Result<T> success(int code,String msg,T data){
        return new Result<>(code,msg,data);
    }

    public static<T> Result<T> fail(){
        return new Result<>(DEFAULT_ERROR_CODE,DEFAULT_ERROR_MESSAGE,null);
    }

    public static<T> Result<T> fail(String msg){
        return new Result<>(DEFAULT_ERROR_CODE,msg,null);
    }

    public static<T> Result<T> fail(int code,String msg){
        return new Result<>(code,msg,null);
    }
}