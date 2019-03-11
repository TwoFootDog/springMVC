package com.commons.aop;

import com.commons.dao.MongoDBDaoImpl;
import com.commons.domain.CustomizeHeaderVO;
import com.commons.exception.ValidException;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
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

    @Setter(onMethod_ = {@Autowired})
    private MongoDBDaoImpl mongoDBDao;

    @Pointcut("execution(* com.*.service.Z*.*(..)) && args(object)")
    private void publicTarget(Object object) {
    }
    @Around("publicTarget(input)")
    public Object headerValidCheck(ProceedingJoinPoint joinPoint, Object input) throws Throwable {
        Object result = null;

        try {
            mongoDBDao.inputDataInsert(input);      // 입력 data를 mongodb에 저장
            result = joinPoint.proceed();           // 메인 서비스 호출
            mongoDBDao.outputDataInsert(result);    // 입력 data를 mongodb에 저장

        } catch (ValidException e) {
            throw new ValidException(e.getAns_cd(), e.getAns_msg());
        } catch (Exception e) {
            throw new ValidException("9999", "시스템실 연락바람");
        }

        return result;
    }
}
