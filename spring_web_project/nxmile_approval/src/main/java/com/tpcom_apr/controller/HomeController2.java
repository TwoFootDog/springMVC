package com.tpcom_apr.controller;

import com.tpcom_apr.domain.service.wrapper.OnmsgchkInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.OnmsgchkOutputWrapperVO;
import com.tpcom_apr.service.serviceInterface.OnmsgchkService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HomeController2 {

    OnmsgchkService onmsgchkService;

    @PostMapping(value = "/onmsgchk",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public OnmsgchkOutputWrapperVO onmsgchk(@RequestBody OnmsgchkInputWrapperVO inputWrapperVO) {
        return onmsgchkService.syncCall(inputWrapperVO);
    }
}
