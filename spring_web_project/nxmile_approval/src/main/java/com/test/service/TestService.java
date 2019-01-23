package com.test.service;

import org.springframework.stereotype.Service;

@Service
public class TestService {

    public boolean numberCompare(int number1, int number2) {
        if (number1 == number2) {
            return true;
        } else {
            return false;
        }
    }
}
