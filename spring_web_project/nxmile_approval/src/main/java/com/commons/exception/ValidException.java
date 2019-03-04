package com.commons.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpHeaders;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidException extends RuntimeException{
//    private HttpHeaders requestHeaders;
    private String ans_cd;
    private String ans_msg;
}
