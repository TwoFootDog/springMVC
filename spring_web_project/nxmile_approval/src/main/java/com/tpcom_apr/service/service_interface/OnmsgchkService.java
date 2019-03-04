package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.OnmsgchkInputVO;
import com.tpcom_apr.domain.service.OnmsgchkOutputVO;
import com.tpcom_apr.domain.service.wrapper.OnmsgchkInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.OnmsgchkOutputWrapperVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface OnmsgchkService {
    public OnmsgchkOutputWrapperVO syncCall(OnmsgchkInputWrapperVO inputWrapperVO);
    public void commonInputDataValidChk(OnmsgchkInputVO inputVO);
}

