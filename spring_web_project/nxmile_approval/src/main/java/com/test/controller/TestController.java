package com.test.controller;


import com.test.domain.BoardVO;
import com.test.service.TestService;
import com.test.service.TestService2;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Setter(onMethod_ = {@Autowired})
    private TestService testService;
    @Setter(onMethod_ = {@Autowired})
    private TestService2 testService2;

    @GetMapping(value = "/board", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public BoardVO getBoard(@RequestParam Long bno) {
        return testService2.getBoardInfo(bno);
    }

    @GetMapping(value = "/test")
    public boolean test(@RequestParam int number1, @RequestParam int number2) {
        return testService.numberCompare(number1, number2);
    }
}
