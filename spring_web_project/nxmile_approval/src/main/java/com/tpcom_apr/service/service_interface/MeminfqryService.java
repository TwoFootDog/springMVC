package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.MeminfqryInputVO;
import com.tpcom_apr.domain.service.MeminfqryOutputVO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface MeminfqryService {
    public ResponseEntity<MeminfqryOutputVO> syncCall(HttpServletRequest request, MeminfqryInputVO inputVO);
}
