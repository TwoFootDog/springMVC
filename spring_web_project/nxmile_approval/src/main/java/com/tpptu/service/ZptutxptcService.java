package com.tpptu.service;

import com.commons.exception.ValidException;
import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import com.tpcom_apr.domain.OritrqryInputVO;
import com.tpcom_apr.domain.OritrqryOutputVO;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import com.tpcom_apr.service.service_interface.OritrqryService;
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
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Log4j
public class ZptutxptcService {

    @Setter(onMethod_ = {@Autowired})
    private OnmsgchkService onmsgchkService;

    @Setter(onMethod_ = {@Autowired})
    private OritrqryService oritrqryService;

    private OnmsgchkInputVO onmsgchkInputVO;
    private OritrqryInputVO oritrqryInputVO;

    private ZptutxptcOutputVO outputVO;
    private HttpHeaders responseHeaders;



    public ResponseEntity<ZptutxptcOutputVO> syncCall(HttpServletRequest request,
                                                      ZptutxptcInputVO zptutxptcInputVO) {

        /* 전문 유효성 체크 */
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

        /* 원거래 조회 */
        oritrqryInputVO = new OritrqryInputVO();
        oritrqryInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        oritrqryInputVO.setTrc_no(request.getHeader("trc_no"));
        oritrqryInputVO.setMbrsh_pgm_id(zptutxptcInputVO.getMbrsh_pgm_id());
        oritrqryInputVO.setMcht_no(zptutxptcInputVO.getMcht_no());
        oritrqryInputVO.setCrd_no(zptutxptcInputVO.getTrack_ii_data());
        oritrqryInputVO.setOrgn_Deal_dy(zptutxptcInputVO.getOrgn_deal_dy());
        oritrqryInputVO.setOrgn_deal_aprv_no(zptutxptcInputVO.getOrgn_deal_aprv_no());
        oritrqryInputVO.setOrgn_deal_coopco_aprv_no(zptutxptcInputVO.getOrgn_deal_coopco_aprv_no());
        oritrqryInputVO.setOrgn_deal_amt(zptutxptcInputVO.getOrgn_deal_amt_pnt());
        oritrqryInputVO.setAns_cd(request.getHeader("ans_cd1"));
        ResponseEntity<OritrqryOutputVO> oritrqryOutputVO = oritrqryService.syncCall(request, oritrqryInputVO);


        /* 회원 조회 */

        /* 포인트 갱신 */


        /* 거래내역 생성 모듈 호출 */



        // 결과값 header setting
        outputVO = new ZptutxptcOutputVO();
        responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        responseHeaders.add("telgrm_no", request.getHeader("telgrm_no"));
        responseHeaders.add("orgn_cd", request.getHeader("orgn_cd"));
        responseHeaders.add("send_dy", new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()));
        responseHeaders.add("send_tm", new SimpleDateFormat("HHmmss").format(System.currentTimeMillis()));
        responseHeaders.add("trc_no", request.getHeader("trc_no"));
        responseHeaders.add("telgrm_fg", request.getHeader("telgrm_fg"));
        responseHeaders.add("data_size", request.getHeader("data_size"));
        responseHeaders.add("ans_cd1", "00");
        responseHeaders.add("ans_cd2", "00");

        // 결과값 body setting
        outputVO.setMbrsh_pgm_id("A");
        outputVO.setAprv_dy("20181219");
        outputVO.setAprv_no("F88888888");
        outputVO.setMcht_no("123456789");
        outputVO.setCrd_no("2222222222222222");



        log.info("cccccccc");

        return new ResponseEntity<ZptutxptcOutputVO>(outputVO, responseHeaders, HttpStatus.OK);
    }
}
