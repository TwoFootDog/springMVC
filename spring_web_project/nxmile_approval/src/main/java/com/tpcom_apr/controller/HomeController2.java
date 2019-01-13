package com.tpcom_apr.controller;

import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@AllArgsConstructor
public class HomeController2 {

    OnmsgchkService onmsgchkService;

    @PostMapping(value = "/onmsgchk",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<OnmsgchkOutputVO> home2(HttpServletRequest header, @RequestBody OnmsgchkInputVO inputVO) {
        return onmsgchkService.syncCall(header, inputVO);
    }
}
