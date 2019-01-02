package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import org.springframework.http.HttpRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

public interface OnmsgchkService {
    public ResponseEntity<OnmsgchkOutputVO> syncCall(HttpServletRequest request, OnmsgchkInputVO inputVO);
    public void commonInputDataValidChk(HttpServletRequest request, OnmsgchkInputVO onmsgchkInputVO);
}
