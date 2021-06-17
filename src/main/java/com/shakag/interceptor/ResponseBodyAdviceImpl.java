package com.shakag.interceptor;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

@ControllerAdvice
public class ResponseBodyAdviceImpl implements ResponseBodyAdvice {

    @Override //返回值为true 下面的 beforeBodyWrite 执行,false不执行
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        /*
            calzz: 调用的 Controller 名称(例：class com.shakag.controller.KaptchaController)
            name: 调用的具体方法名 (例：getKaptcha)
         */
        Method method = methodParameter.getMethod();
        assert method != null;
        Class<?> clazz = method.getDeclaringClass();
        String name = method.getName();

        //排除返回值不需要加密的方法
        Set<String> excludeMethods = new HashSet<>();
        excludeMethods.add("getKaptcha");
        boolean bool = excludeMethods.contains(name);
        return !bool;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //返回数据加密
        try {
            Cipher cipher = Cipher.getInstance("AES");
            SecretKeySpec key = new SecretKeySpec("1234567812345678".getBytes(), "AES");
            cipher.init(Cipher.ENCRYPT_MODE,key);
            byte[] bytes = cipher.doFinal(o.toString().getBytes());
            o = Base64.encode(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return o;
    }
}
