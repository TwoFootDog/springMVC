package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.MempntuptInputVO;
import com.tpcom_apr.domain.service.MempntuptOutputVO;
import com.tpcom_apr.domain.service.wrapper.MempntuptInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.MempntuptOutputWrapperVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface MempntuptService {
    public MempntuptOutputWrapperVO syncCall(MempntuptInputWrapperVO inputWrapperVO);
}
