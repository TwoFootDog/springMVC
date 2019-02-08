package com.tpptu.service;

import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.*;
import com.tpcom_apr.domain.sql.Apr_dealtr_trn_tpcom_vf2001InputVO;
import com.tpcom_apr.domain.sql.Apr_dealtr_trn_tpcom_vf2001OutputVO;
import com.tpcom_apr.mapper.Apr_dealtr_trnMapper;
import com.tpcom_apr.service.service_interface.*;
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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

@Service
@Log4j
@Transactional
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
    @Setter(onMethod_ = {@Autowired})
    private MempntuptService mempntuptService;  // 포인트 갱신
    @Setter(onMethod_ = {@Autowired})
    private CntrinsertService cntrinsertService;    // 취소거래내역생성 및 원거래갱신

    /* 호출 서비스 입출력전문 */
    private OnmsgchkInputVO onmsgchkInputVO;
    private GetaprvnoInputVO getaprvnoInputVO;
    private OritrqryInputVO oritrqryInputVO;
    private MeminfqryInputVO meminfqryInputVO;
    private Apr_dealtr_trn_tpcom_vf2001InputVO apr_dealtr_trn_tpcom_vf2001InputVO;
    private List<Apr_dealtr_trn_tpcom_vf2001OutputVO> apr_dealtr_trn_tpcom_vf2001OutputVO;
    private MempntuptInputVO mempntuptInputVO;
    private CntrinsertInputVO cntrinsertInputVO;

    /* 출력값 */
    private HttpHeaders responseHeaders;
    private ZptutxptcOutputVO outputVO;




    public ResponseEntity<ZptutxptcOutputVO> syncCall(HttpHeaders requestHeader,
                                                      ZptutxptcInputVO inputVO) {

        Map<String, String> header = requestHeader.toSingleValueMap();
        /* 전문 유효성 체크 */
        onmsgchkInputVO = new OnmsgchkInputVO();
        onmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        onmsgchkInputVO.setTelgrm_no(header.get("telgrm_no"));
        onmsgchkInputVO.setOrgan_cd(header.get("organ_cd"));
        onmsgchkInputVO.setTrc_no(header.get("trc_no"));
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
        ResponseEntity<OnmsgchkOutputVO> onmsgchkOutputVO = onmsgchkService.syncCall(requestHeader, onmsgchkInputVO);


        /* 승인번호 채번 */
        getaprvnoInputVO = new GetaprvnoInputVO();
        getaprvnoInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
        getaprvnoInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        getaprvnoInputVO.setMcht_no(inputVO.getMcht_no());
        getaprvnoInputVO.setTelgrm_fg(header.get("telgrm_fg"));
        ResponseEntity<GetaprvnoOutputVO> getaprvnoOutputVO = getaprvnoService.syncCall(requestHeader, getaprvnoInputVO);


        /* 원거래 조회 */
        oritrqryInputVO = new OritrqryInputVO();
        oritrqryInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        oritrqryInputVO.setTrc_no(header.get("trc_no"));
        oritrqryInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
        oritrqryInputVO.setMcht_no(inputVO.getMcht_no());
        oritrqryInputVO.setCrd_no(inputVO.getTrack_ii_data());
        oritrqryInputVO.setOrgn_Deal_dy(inputVO.getOrgn_deal_dy());
        oritrqryInputVO.setOrgn_deal_aprv_no(inputVO.getOrgn_deal_aprv_no());
        oritrqryInputVO.setOrgn_deal_coopco_aprv_no(inputVO.getOrgn_deal_coopco_aprv_no());
        oritrqryInputVO.setOrgn_deal_amt(inputVO.getOrgn_deal_amt_pnt());
        oritrqryInputVO.setAns_cd(header.get("ans_cd1"));
        ResponseEntity<OritrqryOutputVO> oritrqryOutputVO = oritrqryService.syncCall(requestHeader, oritrqryInputVO);


        /* 회원 조회 */
        meminfqryInputVO = new MeminfqryInputVO();
        meminfqryInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
        meminfqryInputVO.setCrd_no(inputVO.getTrack_ii_data());
        meminfqryInputVO.setAprv_no("");
        ResponseEntity<MeminfqryOutputVO> meminfqryOutputVO = meminfqryService.syncCall(requestHeader, meminfqryInputVO);

        /* 취소대상 거래내역 조회 */
        apr_dealtr_trn_tpcom_vf2001InputVO = new Apr_dealtr_trn_tpcom_vf2001InputVO();
        apr_dealtr_trn_tpcom_vf2001InputVO.setMbrsh_pgm_id(oritrqryOutputVO.getBody().getMbrsh_pgm_id());
        apr_dealtr_trn_tpcom_vf2001InputVO.setRep_aprv_no(oritrqryOutputVO.getBody().getRep_aprv_no());
        apr_dealtr_trn_tpcom_vf2001InputVO.setOrgn_aprv_dy(oritrqryOutputVO.getBody().getAprv_dy());
        apr_dealtr_trn_tpcom_vf2001InputVO.setTrc_no(oritrqryOutputVO.getBody().getTrc_no());
        if (!StringUtils.isEmpty(inputVO.getSlp_cd()) && inputVO.getSlp_cd().equals("42")) {
            apr_dealtr_trn_tpcom_vf2001InputVO.setSlp_cd("41");
        } else if (!StringUtils.isEmpty(inputVO.getSlp_cd()) && inputVO.getSlp_cd().equals("22")) {
            apr_dealtr_trn_tpcom_vf2001InputVO.setSlp_cd("11");
        } else {
            throw new ValidException(requestHeader, "4468", "취소대상 거래내역 조회 전표 에러");
        }
        apr_dealtr_trn_tpcom_vf2001OutputVO =
                apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_vf2001(apr_dealtr_trn_tpcom_vf2001InputVO);
        for (Apr_dealtr_trn_tpcom_vf2001OutputVO orgnDealtrOutputVO : apr_dealtr_trn_tpcom_vf2001OutputVO) {

            /* 포인트 갱신 */
            mempntuptInputVO = new MempntuptInputVO();
            mempntuptInputVO.setMbrsh_pgm_id(orgnDealtrOutputVO.getMbrsh_pgm_id());
            mempntuptInputVO.setMbr_id(orgnDealtrOutputVO.getMbr_id());
            mempntuptInputVO.setPnt_knd_cd(orgnDealtrOutputVO.getPnt_knd_cd());
            mempntuptInputVO.setOrgan_cd(header.get("organ_cd"));
            mempntuptInputVO.setCur_pnt(0L);
            mempntuptInputVO.setAvl_pnt(0L);
            ResponseEntity<MempntuptOutputVO> mempntuptOutputVO = mempntuptService.syncCall(requestHeader, mempntuptInputVO);


            /* 취소거래내역 생성 및 원거래 갱신 */
            cntrinsertInputVO = new CntrinsertInputVO();
            cntrinsertInputVO.setMbrsh_pgm_id(orgnDealtrOutputVO.getMbrsh_pgm_id());
            cntrinsertInputVO.setAprv_dy(getaprvnoOutputVO.getBody().getAprv_dy());
            cntrinsertInputVO.setAprv_no(getaprvnoOutputVO.getBody().getAprv_no());
            cntrinsertInputVO.setCrd_no(orgnDealtrOutputVO.getCrd_no());
            cntrinsertInputVO.setAprv_tm(getaprvnoOutputVO.getBody().getAprv_tm());
            cntrinsertInputVO.setDeal_dy(inputVO.getDeal_dy());
            cntrinsertInputVO.setMcht_no(inputVO.getMcht_no());
            cntrinsertInputVO.setIncom_crd_no(orgnDealtrOutputVO.getIncom_crd_no());


        }

        // 결과값 header setting
        responseHeaders = new HttpHeaders();
        responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        responseHeaders.add("telgrm_no", header.get("telgrm_no").substring(0,3).concat("1"));
        responseHeaders.add("organ_cd", header.get("organ_cd"));
        responseHeaders.add("send_dy", new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()));
        responseHeaders.add("send_tm", new SimpleDateFormat("HHmmss").format(System.currentTimeMillis()));
        responseHeaders.add("trc_no", header.get("trc_no"));
        responseHeaders.add("telgrm_fg", header.get("telgrm_fg"));
        responseHeaders.add("data_size", header.get("data_size"));
        responseHeaders.add("ans_cd1", "00");
        responseHeaders.add("ans_cd2", "00");
        responseHeaders.add("filler", "");

        outputVO = new ZptutxptcOutputVO();
        outputVO.setMbrsh_pgm_id("A");
        outputVO.setAprv_dy("20181219");
        outputVO.setAprv_no("F88888888");
        outputVO.setMcht_no("123456789");
        outputVO.setCrd_no("2222222222222222");


        return new ResponseEntity<ZptutxptcOutputVO>(outputVO, responseHeaders, HttpStatus.OK);
    }
}
