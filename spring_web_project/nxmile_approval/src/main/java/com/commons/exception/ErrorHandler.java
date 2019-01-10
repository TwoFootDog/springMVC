package com.commons.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@Log4j
@AllArgsConstructor
public class ErrorHandler {

    public static final ResponseEntity<?> setError(String err_cd, String err_msg) {

        HttpHeaders response = new HttpHeaders();
        response.add("ans_cd", err_cd);

        log.info("biz error ans_cd : " + err_cd + ", error_msg : " + err_msg);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
