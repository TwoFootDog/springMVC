package com.commons.exception;


import lombok.extern.log4j.Log4j;
        import org.springframework.http.HttpHeaders;
        import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
        import org.springframework.web.bind.annotation.ControllerAdvice;
        import org.springframework.web.bind.annotation.ExceptionHandler;
        import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Map;

@ControllerAdvice
@ResponseBody
@Log4j
public class GlobalExceptionHandler{

    @ExceptionHandler(value = ValidException.class)
    public ResponseEntity<?> handleValidException(ValidException e){
        Map<String, String> header = e.getRequestHeaders().toSingleValueMap();

        HttpHeaders responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        responseHeaders.add("telgrm_no", header.get("telgrm_no").substring(0,3).concat("1"));
        responseHeaders.add("organ_cd", header.get("organ_cd"));
        responseHeaders.add("send_dy", new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()));
        responseHeaders.add("send_tm", new SimpleDateFormat("HHmmss").format(System.currentTimeMillis()));
        responseHeaders.add("trc_no", header.get("trc_no"));
        responseHeaders.add("telgrm_fg", header.get("telgrm_fg"));
        responseHeaders.add("data_size", header.get("data_size"));
        responseHeaders.add("ans_cd1", e.getAns_cd().substring(0,2));
        responseHeaders.add("ans_cd2", e.getAns_cd().substring(2,4));
        responseHeaders.add("filler", "");



        log.error("business error occur. ans_cd : " + e.getAns_cd() + ", error message : " + e.getAns_msg());

        return new ResponseEntity<>(responseHeaders, HttpStatus.OK);
    }
}
