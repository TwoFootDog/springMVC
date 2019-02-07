package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.OnmsgchkInputVO;
import com.tpcom_apr.domain.service.OnmsgchkOutputVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface OnmsgchkService {
    public ResponseEntity<OnmsgchkOutputVO> syncCall(HttpHeaders requestHeaders, OnmsgchkInputVO inputVO);
    public void commonInputDataValidChk(HttpHeaders requestHeaders, OnmsgchkInputVO onmsgchkInputVO);
}

