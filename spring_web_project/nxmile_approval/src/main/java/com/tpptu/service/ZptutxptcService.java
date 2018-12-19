package com.tpptu.service;

import com.tpcom_apr.domain.BmComOnmsgchkInputVO;
import com.tpcom_apr.service.BmComOnmsgchkModule;
import com.tpptu.domain.ZptutxptcInputVO;
import com.tpptu.domain.ZptutxptcOutputVO;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ZptutxptcService {


    public ResponseEntity<ZptutxptcOutputVO> syncCall(ZptutxptcInputVO InputVo) {

        // 전문유효성체크 모듈 호출

        // 회원 조회 모듈 호출

        // 원거래 조회 모듈 호출

        //거래내역 생성 모듈 호출



        // 결과값 header setting
        ZptutxptcOutputVO outputVO = new ZptutxptcOutputVO();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.set("svc_name", "zptutxptc0001");
        httpHeaders.set("ans_cd", "0000");

        // 결과값 body setting
        outputVO.setMbrsh_pgm_id("A");
        outputVO.setAprv_dy("20181219");
        outputVO.setAprv_no("F88888888");
        outputVO.setMcht_no("123456789");
        outputVO.setCrd_no("2222222222222222");

        return new ResponseEntity<ZptutxptcOutputVO>(outputVO, httpHeaders, HttpStatus.OK);
    }
}
