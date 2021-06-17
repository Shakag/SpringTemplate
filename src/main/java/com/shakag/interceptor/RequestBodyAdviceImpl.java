package com.shakag.interceptor;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.apache.commons.io.IOUtils;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;

@ControllerAdvice
public class RequestBodyAdviceImpl implements RequestBodyAdvice {
    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return true;
    }

    @Override
    public HttpInputMessage beforeBodyRead(HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) throws IOException {
        try {
            String bodyStr = IOUtils.toString(httpInputMessage.getBody(), StandardCharsets.UTF_8);

            //解密 模式和key要和加密时相同
            Cipher cipher = Cipher.getInstance("AES");
            //AES算法，key的大小必须是16个字节 "1234567812345678"
            SecretKeySpec key = new SecretKeySpec("1234567812345678".getBytes(), "AES");

            cipher.init(Cipher.DECRYPT_MODE,key);
            byte[] decode = Base64.decode(bodyStr);
            byte[] bytes1 = cipher.doFinal(decode);
            String newBodyStr = new String(bytes1);

            return new HttpInputMessage() {
                @Override
                public InputStream getBody() throws IOException {
                    ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(newBodyStr.getBytes());
                    return new ByteArrayInputStream(newBodyStr.getBytes());
                }

                @Override
                public HttpHeaders getHeaders() {
                    return httpInputMessage.getHeaders();
                }
            };
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  httpInputMessage;
    }

    @Override //要把上面方法 解密后的 Object 返回，不然controller 层不能获取到
    public Object afterBodyRead(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return o;
    }

    @Override
    public Object handleEmptyBody(Object o, HttpInputMessage httpInputMessage, MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return null;
    }
}
