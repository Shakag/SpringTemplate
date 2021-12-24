package com.shakag.filter;

import java.util.ArrayList;
import java.util.List;

public class FilterChainRef {
    public static void main(String[] args) {
        Request request = new Request();
        request.requestStr = "requestStr";
        Response response = new Response();
        response.responseStr = "responseStr";

        FilterChain fc = new FilterChain();
        fc.addFilter(new Filter1()).addFilter(new Filter2());
        fc.doFilter(request, response, fc);

        System.out.println(request.requestStr);
        System.out.println(response.responseStr);
    }
}

interface Filter {

    public void doFilter(Request request, Response response, FilterChain chain);

}

class FilterChain implements Filter {

    private List<Filter> filters = new ArrayList<>();
    int index = 0;

    public FilterChain addFilter(Filter filter) {
        filters.add(filter);
        return this;
    }

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {

        if (index == filters.size()) {
            return;
        }
        Filter filter = filters.get(index);
        index++;
        filter.doFilter(request, response, chain);
    }

}

class Request {
    public String requestStr;
}

class Response {
    public String responseStr;
}

class Filter1 implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.requestStr = request.requestStr.replace("<", "[").replace(">", "]") + "-------- Filter1 request ";
        chain.doFilter(request, response, chain);
        response.responseStr = response.responseStr + "-------------Filter1 response ";
    }

}

class Filter2 implements Filter {

    @Override
    public void doFilter(Request request, Response response, FilterChain chain) {
        request.requestStr = request.requestStr + "---------------Filter2 request";
        chain.doFilter(request, response, chain);
        response.responseStr = response.responseStr + "---------------------Filter2 response";
    }
}