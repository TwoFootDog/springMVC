package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.OnmsgchkInputVO;
import com.tpcom_apr.domain.service.OnmsgchkOutputVO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface OnmsgchkService {
    public ResponseEntity<OnmsgchkOutputVO> syncCall(HttpServletRequest request, OnmsgchkInputVO inputVO);
    public void commonInputDataValidChk(OnmsgchkInputVO onmsgchkInputVO);
}

