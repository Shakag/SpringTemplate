package com.shakag.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;

/**
 * 全局异常处理
 */
@ControllerAdvice
@Slf4j
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

    /**
     * 空指针异常
     */
    @ExceptionHandler(NullPointerException.class)
    @ResponseBody
    public String error(NullPointerException e) {
        StackTraceElement[] stackTrace = e.getStackTrace();
        String exceptionMsg = "空指针异常:" + stackTrace[0].getClassName() + ", methodName " + stackTrace[0].getMethodName() + ", lineNumber " + stackTrace[0].getLineNumber();
        log.info("log.info- exceptionMsg = {}", exceptionMsg);
        return exceptionMsg;
    }

    /**
     * 接收参数格式不正确异常
     * defaultMessage的值为 @NotBlank(message = "your info") 自定义的 message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result error(MethodArgumentNotValidException e){
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = null;
        if (fieldError != null) {
            defaultMessage = fieldError.getDefaultMessage();
        }
        return new Result(400,defaultMessage,null);
    }
}