package com.shakag.common;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.sql.SQLException;

/**
 * 全局异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IOException.class)
    @ResponseBody
    public Result<String> error(IOException e) {
        //JDK1.8中,SQLException 和 IOException 的 e.getMessage()不为空。RuntimeException的getMessage()为空
        String message = e.getMessage();
        StackTraceElement[] stackTrace = e.getStackTrace();
        String className = stackTrace[0].getClassName(); //首次发生异常的java class类名称
        String methodName = stackTrace[0].getMethodName(); //首次发生异常的方法名称
        int lineNumber = stackTrace[0].getLineNumber(); //发生异常的行数
        return Result.fail(400, "IOException");
    }

    @ExceptionHandler(SQLException.class)
    @ResponseBody
    public Result<String> error(SQLException e) {
        //e.printStackTrace(); 生产环境禁用
        return Result.fail(400, e.getMessage());
    }


    /**
     * 接收参数格式不正确异常
     * defaultMessage的值为 @NotBlank(message = "your info") 自定义的 message
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseBody
    public Result<String> error(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        FieldError fieldError = bindingResult.getFieldError();
        String defaultMessage = null;
        if (fieldError != null) {
            defaultMessage = fieldError.getDefaultMessage();
        }
        return Result.fail(400, defaultMessage);
    }
}