package com.commons.util;

import org.springframework.context.annotation.Bean;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

public class RequestBodyCachingFilter implements Filter {
    public void init(FilterConfig fc) throws ServletException {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        chain.doFilter(new ContentCachingRequestWrapper((HttpServletRequest)request), response);
    }

    public void destroy() {
    }

    @Bean
    public RequestBodyCachingFilter requestBodyCachingFilter() {
        return new RequestBodyCachingFilter();
    }
}
