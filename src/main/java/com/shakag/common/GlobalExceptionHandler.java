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
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseBody
    public Result<String> error(Exception e){
        e.printStackTrace();
        return Result.fail(400,"Global Exception");
    }

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public Result<String>  error(IOException e){
        e.printStackTrace();
        StackTraceElement[] stackTrace = e.getStackTrace();
        String className = stackTrace[0].getClassName(); //首次发生异常的java class类名称
        String methodName = stackTrace[0].getMethodName(); //首次发生异常的方法名称
        int lineNumber = stackTrace[0].getLineNumber(); //发生异常的行数
        return Result.fail(400,"IOException");
    }

    /**
     * 接收参数格式不正确异常
     * defaultMessage的值为 @NotBlank(message = "your info") 自定义的 message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String>  error(MethodArgumentNotValidException e){
        e.printStackTrace();
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = null;
        if (fieldError != null) {
            defaultMessage = fieldError.getDefaultMessage();
        }
        return Result.fail(400,defaultMessage);
    }
}