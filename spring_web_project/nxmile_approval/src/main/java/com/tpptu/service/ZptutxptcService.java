package com.tpptu.service;

import com.commons.exception.ValidException;
import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import com.tpptu.domain.ZptutxptcInputVO;
import com.tpptu.domain.ZptutxptcOutputVO;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
@Log4j
//@AllArgsConstructor
public class ZptutxptcService {

    @Setter(onMethod_ = {@Autowired})
    private OnmsgchkService onmsgchkService;

    private OnmsgchkInputVO onmsgchkInputVO;

    private ZptutxptcOutputVO outputVO;
    private HttpHeaders ResponseHeaders;



    public ResponseEntity<ZptutxptcOutputVO> syncCall(HttpServletRequest request,
                                                      ZptutxptcInputVO zptutxptcInputVO) {

        onmsgchkInputVO = new OnmsgchkInputVO();
        onmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        onmsgchkInputVO.setTelgrm_no(request.getHeader("telgrm_no"));
        onmsgchkInputVO.setOrgan_cd(request.getHeader("organ_cd"));
        onmsgchkInputVO.setTrc_no(request.getHeader("trc_no"));
        onmsgchkInputVO.setMcht_no(zptutxptcInputVO.getMcht_no());
        onmsgchkInputVO.setMcht_biz_no("");
        onmsgchkInputVO.setDeal_dy(zptutxptcInputVO.getDeal_dy());
        onmsgchkInputVO.setCrd_no(zptutxptcInputVO.getTrack_ii_data());
        onmsgchkInputVO.setResd_no(zptutxptcInputVO.getResd_no());
        onmsgchkInputVO.setOrgn_deal_dy(zptutxptcInputVO.getOrgn_deal_dy());
        onmsgchkInputVO.setOrgn_deal_aprv_no(zptutxptcInputVO.getOrgn_deal_aprv_no());
        onmsgchkInputVO.setOrgn_deal_coopco_aprv_no(zptutxptcInputVO.getOrgn_deal_coopco_aprv_no());
        onmsgchkInputVO.setCncl_req_fg(zptutxptcInputVO.getCash_arcpt_proc_fg());
        onmsgchkInputVO.setDeal_amt_sum(0L);
        onmsgchkInputVO.setDeal_amt1(0L);
        onmsgchkInputVO.setOrgn_deal_amt(0L);


        ResponseEntity<OnmsgchkOutputVO> onmsgchkOutputVO = onmsgchkService.syncCall(request, onmsgchkInputVO);



        // 원거래 조회 모듈 호출


        // 회원 조회 모듈 호출


        //거래내역 생성 모듈 호출

        log.info("bbbbbbbbb");


        // 결과값 header setting
        outputVO = new ZptutxptcOutputVO();
        ResponseHeaders = new HttpHeaders();
        ResponseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        ResponseHeaders.add("ans_cd", "0000");

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
