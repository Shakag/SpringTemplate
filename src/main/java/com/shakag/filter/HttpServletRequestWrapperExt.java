package com.shakag.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Map;

/**
 * 修改或者添加参数
 */

public class HttpServletRequestWrapperExt extends HttpServletRequestWrapper {

    //请求头集合
    private Map<String, String> headerMap = new HashMap<>();

    //参数集合
    private Map<String, String[]> parameterMap = new HashMap<>();



    public HttpServletRequestWrapperExt(HttpServletRequest request) {
        super(request);
        // 将 request 中的参数赋值给当前 map
        parameterMap.putAll(request.getParameterMap());
    }

    public Map<String, String> getHeaderMap() {
        return headerMap;
    }

    public void setHeaderMap(Map<String, String> headerMap) {
        this.headerMap = headerMap;
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    public void setParameterMap(Map<String, String[]> parameterMap) {
        this.parameterMap = parameterMap;
    }

    /**
     * controller不指定注解或注解为RequestParam时
     * 会调用 RequestParamMethodArgumentResolver解析器 之后会调用此方法
     */
    @Override
    public String[] getParameterValues(String name) {
        String[] valueArr = super.getParameterValues(name);
        if (parameterMap.containsKey(name)) {
            return parameterMap.get(name);
        }
        return valueArr;
    }
}
