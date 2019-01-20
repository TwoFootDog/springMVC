package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.MeminfqryInputVO;
import com.tpcom_apr.domain.MeminfqryOutputVO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface MeminfqryService {
    public ResponseEntity<MeminfqryOutputVO> syncCall(HttpServletRequest request, MeminfqryInputVO inputVO);
}
