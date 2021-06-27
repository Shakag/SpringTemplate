package com.shakag.filter;

import com.alibaba.fastjson.JSON;
import com.shakag.common.Result;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, FilterChain filterChain) throws ServletException, IOException {
        String method = httpServletRequest.getMethod();
        String url = httpServletRequest.getRequestURI();
        if("/login".equals(url) && method.equals("POST")){
            String kaptcha = httpServletRequest.getParameter("kaptcha");
            if(kaptcha != null && kaptcha.equals("abcd")){
                filterChain.doFilter(httpServletRequest, httpServletResponse);
            }else{
                ServletOutputStream os = httpServletResponse.getOutputStream();
                httpServletResponse.setContentType("application/json;charset=utf-8");
                Result failResult = new Result(0, "验证码不正确", null);
                os.write(JSON.toJSONBytes(failResult));
            }
        }else{
            filterChain.doFilter(httpServletRequest, httpServletResponse);
        }
    }


}
