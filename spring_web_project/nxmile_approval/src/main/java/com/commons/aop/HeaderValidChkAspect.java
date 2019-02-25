package com.commons.aop;

import com.commons.domain.CustomizeHeaderVO;
import lombok.extern.log4j.Log4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

@Aspect
@Log4j
@Component
public class HeaderValidChkAspect {

    @Pointcut("execution(* com.*.service.Z*.*(..)) && args(request, object)")
    private void publicTarget(HttpHeaders request, Object object) {
    }
    @Around("publicTarget(request, object)")
    public Object headerValidCheck(ProceedingJoinPoint joinPoint, HttpHeaders request, Object object) throws Throwable {
        Object result = null;

        try {
            result = joinPoint.proceed(new Object[] {request, object});

            log.info("aop result =============================> " + request);
        } catch (Exception e) {
//            e.printStackTrace();
//            ResponseEntity<?> result1 = (ResponseEntity<?>)result;
////            log.info("aop result =============================> " + result1);
////            log.info("aop request =============================> " + request);
            log.info("error message====================>" + e.getMessage());
            log.info("error stack====================>" + e.getStackTrace());
            log.info("hhh");


        }

        return result;
    }
}
