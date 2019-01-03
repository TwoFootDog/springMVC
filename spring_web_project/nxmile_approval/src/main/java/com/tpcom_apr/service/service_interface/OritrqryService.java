package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.OritrqryInputVO;
import com.tpcom_apr.domain.OritrqryOutputVO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface OritrqryService {
    public ResponseEntity<OritrqryOutputVO> syncCall(HttpServletRequest request, OritrqryInputVO inputVO);
}
