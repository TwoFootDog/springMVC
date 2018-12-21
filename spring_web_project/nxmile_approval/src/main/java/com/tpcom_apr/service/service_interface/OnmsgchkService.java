package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface OnmsgchkService {
    public ResponseEntity<OnmsgchkOutputVO> syncCall(@RequestBody OnmsgchkInputVO inputVO);
}
