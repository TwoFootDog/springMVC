package com.commons.exception;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValidException extends RuntimeException{
    private String ans_cd;
    private String ans_msg;
}
