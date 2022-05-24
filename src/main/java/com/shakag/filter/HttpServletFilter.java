package com.shakag.filter;

import org.springframework.stereotype.Component;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

@Component
public class HttpServletFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        Map<String, String[]> parameterMap = request.getParameterMap();

        //向httpServletRequest中修改或添加新参数
        parameterMap.put("name",new String[]{"tom"});
        HttpServletRequestWrapperExt httpServletRequestWrapperExt = new HttpServletRequestWrapperExt((HttpServletRequest) request, parameterMap);

        //继续向后传递修改后的request
        chain.doFilter(httpServletRequestWrapperExt, response);
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }


}
