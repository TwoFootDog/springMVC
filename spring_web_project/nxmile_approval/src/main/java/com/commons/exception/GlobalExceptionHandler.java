package com.commons.exception;


import com.commons.domain.CustomizeHeaderVO;
import com.commons.domain.ErrorOutputWrapperVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContext;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@ControllerAdvice
@ResponseBody
@Log4j
public class GlobalExceptionHandler{

    // request header
    @Setter(onMethod_ = {@Autowired})
    private CustomizeHeaderVO header;

    // response header
    private HttpHeaders responseHeader;

    // 에러 output(header + body)
    ErrorOutputWrapperVO outputWrapperVO;

    @ExceptionHandler(value = ValidException.class)
    public ResponseEntity<ErrorOutputWrapperVO> handleValidException(ValidException e) {

        outputWrapperVO = new ErrorOutputWrapperVO();
        header.setTelgrm_no(header.getTelgrm_no().substring(0, 3).concat("1"));
        header.setSend_dy(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        header.setSend_tm(new SimpleDateFormat("yyyyMMdd").format(new Date()));
        header.setAns_cd1(e.getAns_cd().substring(0, 2));
        header.setAns_cd2(e.getAns_cd().substring(2, 4));
        header.setMessage(e.getAns_msg());
        outputWrapperVO.setHeader(header);

        responseHeader = new HttpHeaders();
        responseHeader.setContentType(MediaType.APPLICATION_JSON);

        return new ResponseEntity<ErrorOutputWrapperVO>(outputWrapperVO, responseHeader, HttpStatus.OK );
    }
}
