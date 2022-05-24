package com.shakag.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@Component
public class HttpServletFilter implements javax.servlet.Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequestWrapperExt requestWrapper = new HttpServletRequestWrapperExt((HttpServletRequest) request);

        //修改请求参数值
        Map<String, String[]> parameterMap = new HashMap<>(request.getParameterMap());
        parameterMap.put("name", new String[]{"tom"});
        requestWrapper.setParameterMap(parameterMap);

        //继续向后传递修改后的request
        chain.doFilter(requestWrapper, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


}
