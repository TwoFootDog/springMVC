package com.commons.exception;


import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
@Log4j
public class GlobalExceptionHandler{

    @ExceptionHandler(value = ValidException.class)
    public ResponseEntity<?> handleValidException(ValidException e){

        HttpHeaders responseHeader = new HttpHeaders();
        responseHeader.add("ans_cd", e.getAns_cd());


        log.error("business error occur. ans_cd : " + e.getAns_cd() + ", error message : " + e.getAns_msg());

        return new ResponseEntity<>(responseHeader, HttpStatus.OK);
    }
}
