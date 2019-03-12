package com.tpcom_apr.service.serviceInterface;

import com.tpcom_apr.domain.service.wrapper.MempntuptInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.MempntuptOutputWrapperVO;

public interface MempntuptService {
    public MempntuptOutputWrapperVO syncCall(MempntuptInputWrapperVO inputWrapperVO);
}
