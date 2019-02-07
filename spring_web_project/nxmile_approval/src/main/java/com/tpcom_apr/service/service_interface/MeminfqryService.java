package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.MeminfqryInputVO;
import com.tpcom_apr.domain.service.MeminfqryOutputVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface MeminfqryService {
    public ResponseEntity<MeminfqryOutputVO> syncCall(HttpHeaders requestHeaders, MeminfqryInputVO inputVO);
}
