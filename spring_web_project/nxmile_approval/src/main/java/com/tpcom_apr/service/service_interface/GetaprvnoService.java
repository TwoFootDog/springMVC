package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.GetaprvnoInputVO;
import com.tpcom_apr.domain.service.GetaprvnoOutputVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface GetaprvnoService {
    public ResponseEntity<GetaprvnoOutputVO> syncCall(HttpHeaders requestHeaders, GetaprvnoInputVO inputVO);
}
