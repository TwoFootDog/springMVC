package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.service.CntrinsertInputVO;
import com.tpcom_apr.domain.service.CntrinsertOutputVO;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;

public interface CntrinsertService {
    public ResponseEntity<CntrinsertOutputVO> syncCall(HttpHeaders requestHeaders, CntrinsertInputVO inputVO );
}
