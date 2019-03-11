package com.commons.exception;


import com.commons.dao.inserface.MongoDBDao;
import com.commons.domain.CustomizeHeaderVO;
import com.commons.domain.ErrorOutputWrapperVO;
import com.commons.util.CustomJsonHttpMessageConverter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Charsets;
import com.tpptu.domain.wrapper.ZptutxptcOutputWrapperVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.util.ContentCachingRequestWrapper;

import javax.inject.Inject;
import javax.servlet.ServletRequest;
import javax.servlet.ServletRequestWrapper;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
@ResponseBody
@Log4j
public class GlobalExceptionHandler{

    @Setter(onMethod_ = {@Autowired})
    private MongoDBDao mongoDBDao;

    // request header
    @Setter(onMethod_ = {@Autowired})
    private CustomizeHeaderVO header;

    // response header
    private HttpHeaders responseHeader;

    // 에러 output(header + body)
    ErrorOutputWrapperVO outputWrapperVO;

    private ContentCachingRequestWrapper getUnderlyingCachingRequest(ServletRequest request) {
        if (ContentCachingRequestWrapper.class.isAssignableFrom(request.getClass())) {
            return (ContentCachingRequestWrapper) request;
        }
        if (request instanceof ServletRequestWrapper) {
            return getUnderlyingCachingRequest(((ServletRequestWrapper)request).getRequest());
        }
        return null;
    }


    @ExceptionHandler(value = ValidException.class)
    public ResponseEntity<ErrorOutputWrapperVO> handleValidException(ValidException e, HttpServletRequest request) throws IOException {

        log.info("=======================req=============");
        log.info("=======================httpservletrequest=============" + request.getHeader("telgrm_no"));
//
//        ObjectMapper obj = new ObjectMapper();
//        log.info("------------------------" + (ZptutxptcOutputWrapperVO)request.getAttribute("zptutxptcOutputWrapperVO"));
//        log.info("=======================httpservletrequest1=============" + ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest().getAttributeNames());


        log.info("########################################" + request.getAttribute(CustomJsonHttpMessageConverter.REQUEST_BODY_ATTRIBUTE_NAME));
        ContentCachingRequestWrapper underlyingCachingRequest = getUnderlyingCachingRequest(request);
        String body = new String(underlyingCachingRequest.getContentAsByteArray(), Charsets.UTF_8);
        log.info("%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%" + body);


        outputWrapperVO = new ErrorOutputWrapperVO();
        header.setTelgrm_no(header.getTelgrm_no().substring(0, 3).concat("1"));
        header.setSend_dy(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        header.setSend_tm(new SimpleDateFormat("HHmmss").format(new Date()));
        header.setAns_cd1(e.getAns_cd().substring(0, 2));
        header.setAns_cd2(e.getAns_cd().substring(2, 4));
        header.setMessage(e.getAns_msg());
        outputWrapperVO.setHeader(header);

        responseHeader = new HttpHeaders();
        responseHeader.setContentType(MediaType.APPLICATION_JSON);

        ResponseEntity<ErrorOutputWrapperVO> result = new ResponseEntity<ErrorOutputWrapperVO>(outputWrapperVO, responseHeader, HttpStatus.OK);
        mongoDBDao.outputDataInsert(result);    // 에러 출력값을 mongodb 에 저장

        return result;
    }
}
