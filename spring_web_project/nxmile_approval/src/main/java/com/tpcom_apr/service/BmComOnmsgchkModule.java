package com.tpcom_apr.service;

import com.tpcom_apr.domain.BmComOnmsgchkInputVO;
import com.tpcom_apr.domain.BmComOnmsgchkOutputVO;
import com.tpcom_apr.mapper.Rul_svcavl_conMapper;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Log4j
@AllArgsConstructor
public class BmComOnmsgchkModule {
    Rul_svcavl_conMapper rul_svcavl_conMapper;

    public ResponseEntity<BmComOnmsgchkOutputVO> syncCall(@RequestBody BmComOnmsgchkInputVO inputVO) {


        BmComOnmsgchkOutputVO outputVO = new BmComOnmsgchkOutputVO();
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<BmComOnmsgchkOutputVO>(outputVO, httpHeaders, HttpStatus.OK);
    }
}
