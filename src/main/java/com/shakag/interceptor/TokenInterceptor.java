package com.shakag.interceptor;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.shakag.common.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;

@Component
public class TokenInterceptor implements HandlerInterceptor {
    private static String SECRETKEY = "secret key";
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String token = (String) request.getParameter("token");
        DecodedJWT decodedJWT = JWT.decode(token);

        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream os = response.getOutputStream();

        //验证jwt
        try {
            Algorithm.HMAC256(SECRETKEY).verify(decodedJWT);
            long time = decodedJWT.getExpiresAt().getTime();
            //判断token是否过期
            if(time < System.currentTimeMillis()){
                Result result = new Result(400, "token expired", null);
                os.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
                return false;
            }
        }catch (Exception e){
            //进入异常, token 被篡改, 返回json信息给前端
            Result result = new Result(400, "token invalid", null);
            os.write(JSON.toJSONString(result).getBytes(StandardCharsets.UTF_8));
            return false;
        }finally {
            os.flush();
            os.close();
        }

        return true;
    }
}
