package com.tpcom_apr.service.serviceInterface;

import com.tpcom_apr.domain.service.OnmsgchkInputVO;
import com.tpcom_apr.domain.service.wrapper.OnmsgchkInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.OnmsgchkOutputWrapperVO;

public interface OnmsgchkService {
    public OnmsgchkOutputWrapperVO syncCall(OnmsgchkInputWrapperVO inputWrapperVO);
    public void commonInputDataValidChk(OnmsgchkInputVO inputVO);
}

