package com.tpcom_apr.service.serviceInterface;

import com.tpcom_apr.domain.service.wrapper.GetaprvnoInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.GetaprvnoOutputWrapperVO;

public interface GetaprvnoService {
    public GetaprvnoOutputWrapperVO syncCall(GetaprvnoInputWrapperVO inputWrapperVO);
}
