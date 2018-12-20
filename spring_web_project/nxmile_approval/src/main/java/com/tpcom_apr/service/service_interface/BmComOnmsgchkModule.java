package com.tpcom_apr.service.service_interface;

import com.tpcom_apr.domain.BmComOnmsgchkInputVO;
import com.tpcom_apr.domain.BmComOnmsgchkOutputVO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface BmComOnmsgchkModule {
    public ResponseEntity<BmComOnmsgchkOutputVO> syncCall(@RequestBody BmComOnmsgchkInputVO inputVO);
}
