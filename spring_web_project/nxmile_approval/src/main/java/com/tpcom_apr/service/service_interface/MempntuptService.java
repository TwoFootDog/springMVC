package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.MempntuptInputVO;
import com.tpcom_apr.domain.service.MempntuptOutputVO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface MempntuptService {
    public ResponseEntity<MempntuptOutputVO> syncCall(HttpServletRequest request, MempntuptInputVO inputVO);
}
