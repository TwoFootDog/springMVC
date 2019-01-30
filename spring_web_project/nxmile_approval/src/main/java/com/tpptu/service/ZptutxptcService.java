package com.tpptu.service;

import com.tpcom_apr.domain.service.*;
import com.tpcom_apr.domain.sql.Apr_dealtr_trn_tpcom_vf2001InputVO;
import com.tpcom_apr.mapper.Apr_dealtr_trnMapper;
import com.tpcom_apr.service.service_interface.GetaprvnoService;
import com.tpcom_apr.service.service_interface.MeminfqryService;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import com.tpcom_apr.service.service_interface.OritrqryService;
import com.tpptu.domain.ZptutxptcInputVO;
import com.tpptu.domain.ZptutxptcOutputVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;

@Service
@Log4j
public class ZptutxptcService {

    /* 호출 서비스 */
    @Setter(onMethod_ = {@Autowired})
    private OnmsgchkService onmsgchkService;    // 전문 유효성 체크
    @Setter(onMethod_ = {@Autowired})
    private GetaprvnoService getaprvnoService;  // 승인번호 채번
    @Setter(onMethod_ = {@Autowired})
    private OritrqryService oritrqryService;    // 원거래 조회
    @Setter(onMethod_ = {@Autowired})
    private MeminfqryService meminfqryService;  // 포인트 조회
    @Setter(onMethod_ = {@Autowired})
    private Apr_dealtr_trnMapper apr_dealtr_trnMapper;  // 거래내역조회 관련 SQL

    /* 호출 서비스 입력전문 */
    private OnmsgchkInputVO onmsgchkInputVO;
    private GetaprvnoInputVO getaprvnoInputVO;
    private OritrqryInputVO oritrqryInputVO;
    private MeminfqryInputVO meminfqryInputVO;
    private Apr_dealtr_trn_tpcom_vf2001InputVO apr_dealtr_trn_tpcom_vf2001InputVO;

    /* 출력값 */
    private HttpHeaders responseHeaders;
    private ZptutxptcOutputVO outputVO;




    public ResponseEntity<ZptutxptcOutputVO> syncCall(HttpServletRequest request,
                                                      ZptutxptcInputVO inputVO) {

        /* 전문 유효성 체크 */
        onmsgchkInputVO = new OnmsgchkInputVO();
        onmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        onmsgchkInputVO.setTelgrm_no(request.getHeader("telgrm_no"));
        onmsgchkInputVO.setOrgan_cd(request.getHeader("organ_cd"));
        onmsgchkInputVO.setTrc_no(request.getHeader("trc_no"));
        onmsgchkInputVO.setMcht_no(inputVO.getMcht_no());
        onmsgchkInputVO.setMcht_biz_no("");
        onmsgchkInputVO.setDeal_dy(inputVO.getDeal_dy());
        onmsgchkInputVO.setCrd_no(inputVO.getTrack_ii_data());
        onmsgchkInputVO.setResd_no(inputVO.getResd_no());
        onmsgchkInputVO.setOrgn_deal_dy(inputVO.getOrgn_deal_dy());
        onmsgchkInputVO.setOrgn_deal_aprv_no(inputVO.getOrgn_deal_aprv_no());
        onmsgchkInputVO.setOrgn_deal_coopco_aprv_no(inputVO.getOrgn_deal_coopco_aprv_no());
        onmsgchkInputVO.setCncl_req_fg(inputVO.getCash_arcpt_proc_fg());
        onmsgchkInputVO.setDeal_amt_sum(0L);
        onmsgchkInputVO.setDeal_amt1(0L);
        onmsgchkInputVO.setOrgn_deal_amt(0L);
        ResponseEntity<OnmsgchkOutputVO> onmsgchkOutputVO = onmsgchkService.syncCall(request, onmsgchkInputVO);


        /* 승인번호 채번 */
        getaprvnoInputVO = new GetaprvnoInputVO();
        getaprvnoInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
        getaprvnoInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        getaprvnoInputVO.setMcht_no(inputVO.getMcht_no());
        getaprvnoInputVO.setTelgrm_fg(request.getHeader("telgrm_fg"));
        ResponseEntity<GetaprvnoOutputVO> getaprvnoOutputVO = getaprvnoService.syncCall(request, getaprvnoInputVO);


        /* 원거래 조회 */
        oritrqryInputVO = new OritrqryInputVO();
        oritrqryInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        oritrqryInputVO.setTrc_no(request.getHeader("trc_no"));
        oritrqryInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
        oritrqryInputVO.setMcht_no(inputVO.getMcht_no());
        oritrqryInputVO.setCrd_no(inputVO.getTrack_ii_data());
        oritrqryInputVO.setOrgn_Deal_dy(inputVO.getOrgn_deal_dy());
        oritrqryInputVO.setOrgn_deal_aprv_no(inputVO.getOrgn_deal_aprv_no());
        oritrqryInputVO.setOrgn_deal_coopco_aprv_no(inputVO.getOrgn_deal_coopco_aprv_no());
        oritrqryInputVO.setOrgn_deal_amt(inputVO.getOrgn_deal_amt_pnt());
        oritrqryInputVO.setAns_cd(request.getHeader("ans_cd1"));
        ResponseEntity<OritrqryOutputVO> oritrqryOutputVO = oritrqryService.syncCall(request, oritrqryInputVO);


        /* 회원 조회 */
        meminfqryInputVO = new MeminfqryInputVO();
        meminfqryInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
        meminfqryInputVO.setCrd_no(inputVO.getTrack_ii_data());
        meminfqryInputVO.setAprv_no("");
        ResponseEntity<MeminfqryOutputVO> meminfqryOutputVo = meminfqryService.syncCall(request, meminfqryInputVO);

        /* 취소대상 거래내역 조회 */
        apr_dealtr_trn_tpcom_vf2001InputVO = new Apr_dealtr_trn_tpcom_vf2001InputVO();
        apr_dealtr_trn_tpcom_vf2001InputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
        apr_dealtr_trn_tpcom_vf2001InputVO.setRep_aprv_no(oritrqryOutputVO.getBody().getRep_aprv_no());
        apr_dealtr_trn_tpcom_vf2001InputVO.setOrgn_aprv_dy(oritrqryOutputVO.getBody().getOrgn_aprv_dy());
        apr_dealtr_trn_tpcom_vf2001InputVO.setTrc_no(request.getHeader("trc_no"));
        if (!StringUtils.isEmpty(inputVO.getSlp_cd()))


        /* 포인트 갱신 */


        /* 거래내역 생성 모듈 호출 */



        // 결과값 header setting
        outputVO = new ZptutxptcOutputVO();
        responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        responseHeaders.add("telgrm_no", request.getHeader("telgrm_no"));
        responseHeaders.add("organ_cd", request.getHeader("organ_cd"));
        responseHeaders.add("send_dy", new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()));
        responseHeaders.add("send_tm", new SimpleDateFormat("HHmmss").format(System.currentTimeMillis()));
        responseHeaders.add("trc_no", request.getHeader("trc_no"));
        responseHeaders.add("telgrm_fg", request.getHeader("telgrm_fg"));
        responseHeaders.add("data_size", request.getHeader("data_size"));
        responseHeaders.add("ans_cd1", "00");
        responseHeaders.add("ans_cd2", "00");
        responseHeaders.add("filler", "");

        // 결과값 body setting
        outputVO.setMbrsh_pgm_id("A");
        outputVO.setAprv_dy("20181219");
        outputVO.setAprv_no("F88888888");
        outputVO.setMcht_no("123456789");
        outputVO.setCrd_no("2222222222222222");


        return new ResponseEntity<ZptutxptcOutputVO>(outputVO, responseHeaders, HttpStatus.OK);
    }
}
