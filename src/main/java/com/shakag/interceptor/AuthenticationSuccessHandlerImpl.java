package com.shakag.interceptor;

import com.alibaba.fastjson.JSON;
import com.shakag.common.Result;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        ServletOutputStream os = httpServletResponse.getOutputStream();
        Result result = new Result(0, "AuthenticationSuccessHandler.......", null);
        os.write(JSON.toJSONBytes(result));
    }
}
