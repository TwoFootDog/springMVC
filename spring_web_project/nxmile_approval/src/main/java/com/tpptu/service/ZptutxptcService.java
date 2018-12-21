package com.tpptu.service;

import com.commons.domain.CustomizeHeaderVO;
import com.google.gson.Gson;
import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import com.tpptu.domain.ZptutxptcInputVO;
import com.tpptu.domain.ZptutxptcOutputVO;
import jdk.nashorn.internal.parser.JSONParser;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Map;

@Service
@Log4j
@AllArgsConstructor
public class ZptutxptcService {
    OnmsgchkService onmsgchkService;

    public ResponseEntity<ZptutxptcOutputVO> syncCall(String header,
                                                      ZptutxptcInputVO inputVO) {
        log.info("aaaaaaaaa");
        // 전문유효성체크 모듈 호출
        JSONParser parser = new JSONParser();
        Object obj = (Object) parser.parse(header);

        log.info(header);
//        OnmsgchkOutputVO onmsgchkOutputVO = onmsgchkService.syncCall(new OnmsgchkInputVO());

        // 회원 조회 모듈 호출

        // 원거래 조회 모듈 호출

        //거래내역 생성 모듈 호출

        log.info("bbbbbbbbb");

        // 결과값 header setting
        ZptutxptcOutputVO outputVO = new ZptutxptcOutputVO();
        HttpHeaders ResponseHeaders = new HttpHeaders();
        ResponseHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseHeaders.set("svc_name", "zptutxptc0001");
        ResponseHeaders.set("ans_cd", "0000");

        // 결과값 body setting
        outputVO.setMbrsh_pgm_id("A");
        outputVO.setAprv_dy("20181219");
        outputVO.setAprv_no("F88888888");
        outputVO.setMcht_no("123456789");
        outputVO.setCrd_no("2222222222222222");

        log.info("cccccccc");

        return new ResponseEntity<ZptutxptcOutputVO>(outputVO, ResponseHeaders, HttpStatus.OK);
    }
}
