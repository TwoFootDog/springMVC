package com.commons.aop;

import com.commons.domain.CustomizeHeaderVO;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Log4j
@Component
public class HeaderValidChkAspect {

    @Pointcut("execution(* com.*.service.Z*.*(..)) && args(request, object)")
    private void publicTarget(HttpServletRequest request, Object object) {
    }
    @Around("publicTarget(request, object)")
    public Object headerValidCheck(ProceedingJoinPoint joinPoint, HttpServletRequest request, Object object) {
        Object result = null;
        log.info("header1111 : " + request.getHeader("Content-Type"));

//            log.info(headerVO);
//        Enumeration<String> headerNames = request.getHeaderNames();
//        while(headerNames.hasMoreElements()) {
//
//        }
        try {
            result = joinPoint.proceed(new Object[] {request, object});
        } catch (Throwable e) {
            e.printStackTrace();
        }

        return result;
    }
}
