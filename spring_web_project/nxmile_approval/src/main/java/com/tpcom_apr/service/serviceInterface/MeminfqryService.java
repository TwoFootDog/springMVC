package com.tpcom_apr.service.serviceInterface;

import com.tpcom_apr.domain.service.wrapper.MeminfqryInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.MeminfqryOutputWrapperVO;

public interface MeminfqryService {
    public MeminfqryOutputWrapperVO syncCall(MeminfqryInputWrapperVO inputWrapperVO);
}
