package com.tpcom_apr.service;

import com.tpcom_apr.domain.OritrqryInputVO;
import com.tpcom_apr.domain.OritrqryOutputVO;
import com.tpcom_apr.service.service_interface.OritrqryService;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public class OritqryServiceImpl implements OritrqryService {

    @Override
    public ResponseEntity<OritrqryOutputVO> syncCall(HttpServletRequest request, OritrqryInputVO inputVO) {
        return null;
    }
}
