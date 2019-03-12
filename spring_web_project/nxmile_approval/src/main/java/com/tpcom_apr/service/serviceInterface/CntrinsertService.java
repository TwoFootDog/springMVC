package com.tpcom_apr.service.serviceInterface;

import com.tpcom_apr.domain.service.wrapper.CntrinsertInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.CntrinsertOutputWrapperVO;

public interface CntrinsertService {
    public CntrinsertOutputWrapperVO syncCall(CntrinsertInputWrapperVO inputWrapperVO);
}
