package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.GetaprvnoInputVO;
import com.tpcom_apr.domain.service.GetaprvnoOutputVO;
import org.springframework.http.ResponseEntity;

import javax.servlet.http.HttpServletRequest;

public interface GetaprvnoService {
    public ResponseEntity<GetaprvnoOutputVO> syncCall(HttpServletRequest request, GetaprvnoInputVO inputVO);
}
