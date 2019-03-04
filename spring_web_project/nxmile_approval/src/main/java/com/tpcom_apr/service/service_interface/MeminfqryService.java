package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.MeminfqryInputVO;
import com.tpcom_apr.domain.service.MeminfqryOutputVO;
import com.tpcom_apr.domain.service.wrapper.MeminfqryInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.MeminfqryOutputWrapperVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface MeminfqryService {
    public MeminfqryOutputWrapperVO syncCall(MeminfqryInputWrapperVO inputWrapperVO);
}
