package com.commons.aop;

import com.commons.domain.CustomizeHeaderVO;
import com.commons.exception.ValidException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpptu.domain.ZptutxptcOutputVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import net.minidev.json.JSONObject;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;



@Aspect
@Log4j
@Component
public class HeaderValidChkAspect {

    @Setter(onMethod_ = {@Autowired})
    private CustomizeHeaderVO header;

    @Pointcut("execution(* com.*.service.Z*.*(..))")
    private void publicTarget() {
    }
    @Around("publicTarget()")
    public Object headerValidCheck(ProceedingJoinPoint joinPoint) throws Throwable {
        Object result = null;

        try {
            result = joinPoint.proceed();

        } catch (ValidException e) {
            throw new ValidException(e.getAns_cd(), e.getAns_msg());
        } catch (Exception e) {
            e.getStackTrace();
            throw new ValidException("9999", "시스템실 연락바람");
        }

        return result;
    }
}
