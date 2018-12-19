package com.tpcom_apr.service;

import com.tpcom_apr.domain.BmComOnmsgchkInputVO;
import com.tpcom_apr.domain.BmComOnmsgchkOutputVO;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class BmComOnmsgchkModule {

    public ResponseEntity<BmComOnmsgchkOutputVO> syncCall(@RequestBody BmComOnmsgchkInputVO inputVO) {

    }
}
