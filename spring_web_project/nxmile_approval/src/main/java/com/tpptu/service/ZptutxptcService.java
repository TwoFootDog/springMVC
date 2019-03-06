package com.tpptu.service;

import com.commons.domain.CustomizeHeaderVO;
import com.commons.exception.ValidException;
import com.commons.service.CommonFunction;
import com.tpcom_apr.domain.service.*;
import com.tpcom_apr.domain.service.wrapper.*;
import com.tpcom_apr.domain.sql.Apr_dealtr_trn_tpcom_vf2001InputVO;
import com.tpcom_apr.domain.sql.Apr_dealtr_trn_tpcom_vf2001OutputVO;
import com.tpcom_apr.mapper.Apr_dealtr_trnMapper;
import com.tpcom_apr.service.service_interface.*;
import com.tpptu.domain.ZptutxptcInputVO;
import com.tpptu.domain.wrapper.ZptutxptcInputWrapperVO;
import com.tpptu.domain.ZptutxptcOutputVO;
import com.tpptu.domain.wrapper.ZptutxptcOutputWrapperVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

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


    /* 호출 서비스 입력전문(header + body) */
    private OnmsgchkInputWrapperVO onmsgchkInputWrapperVO;
    private GetaprvnoInputWrapperVO getaprvnoInputWrapperVO;
    private OritrqryInputWrapperVO oritrqryInputWrapperVO;
    private MeminfqryInputWrapperVO meminfqryInputWrapperVO;
    private MempntuptInputWrapperVO mempntuptInputWrapperVO;
    private CntrinsertInputWrapperVO cntrinsertInputWrapperVO;

    /* 서비스 입력 custom header*/
    private CustomizeHeaderVO header;

    /* 서비스 입력전문(body)*/
    private OnmsgchkInputVO onmsgchkInputVO;
    private GetaprvnoInputVO getaprvnoInputVO;
    private OritrqryInputVO oritrqryInputVO;
    private MeminfqryInputVO meminfqryInputVO;
    private Apr_dealtr_trn_tpcom_vf2001InputVO apr_dealtr_trn_tpcom_vf2001InputVO;
    private List<Apr_dealtr_trn_tpcom_vf2001OutputVO> apr_dealtr_trn_tpcom_vf2001OutputVO;
    private MempntuptInputVO mempntuptInputVO;
    private CntrinsertInputVO cntrinsertInputVO;

    /* 출력값 */
    private ZptutxptcOutputVO outputVO;
    private List<ZptutxptcOutputVO> outputVOList;
    private ZptutxptcOutputWrapperVO outputWrapperVO;

    public ZptutxptcOutputWrapperVO syncCall(ZptutxptcInputWrapperVO inputWrapperVO) {

        int bodyCount = 0;
        header = inputWrapperVO.getHeader();
        outputWrapperVO = new ZptutxptcOutputWrapperVO();   // 사용취소 출력객체 생성

        for (ZptutxptcInputVO inputVO : inputWrapperVO.getBody()) {
            log.info(bodyCount + "번째 데이터 : " + inputVO);
            bodyCount++;

            /* 전문 유효성 체크 */
            onmsgchkInputVO = new OnmsgchkInputVO();
            onmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
            onmsgchkInputVO.setTelgrm_no(header.getTelgrm_no());
            onmsgchkInputVO.setOrgan_cd(header.getOrgan_cd());
            onmsgchkInputVO.setTrc_no(header.getTrc_no());
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

            onmsgchkInputWrapperVO = new OnmsgchkInputWrapperVO();
            onmsgchkInputWrapperVO.setHeader(header);
            onmsgchkInputWrapperVO.setBody(onmsgchkInputVO);
            OnmsgchkOutputWrapperVO onmsgchkOutputVO = onmsgchkService.syncCall(onmsgchkInputWrapperVO);



            /* 승인번호 채번 */
            getaprvnoInputVO = new GetaprvnoInputVO();
            getaprvnoInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
            getaprvnoInputVO.setSvc_modu_id("ZPTUTXPTC0001");
            getaprvnoInputVO.setMcht_no(inputVO.getMcht_no());
            getaprvnoInputVO.setTelgrm_fg(header.getTelgrm_fg());

            getaprvnoInputWrapperVO = new GetaprvnoInputWrapperVO();
            getaprvnoInputWrapperVO.setHeader(header);
            getaprvnoInputWrapperVO.setBody(getaprvnoInputVO);
            GetaprvnoOutputWrapperVO getaprvnoOutputWrapperVO = getaprvnoService.syncCall(getaprvnoInputWrapperVO);



            /* 원거래 조회 */
            oritrqryInputVO = new OritrqryInputVO();
            oritrqryInputVO.setSvc_modu_id("ZPTUTXPTC0001");
            oritrqryInputVO.setTrc_no(header.getTrc_no());
            oritrqryInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
            oritrqryInputVO.setMcht_no(inputVO.getMcht_no());
            oritrqryInputVO.setCrd_no(inputVO.getTrack_ii_data());
            oritrqryInputVO.setOrgn_Deal_dy(inputVO.getOrgn_deal_dy());
            oritrqryInputVO.setOrgn_deal_aprv_no(inputVO.getOrgn_deal_aprv_no());
            oritrqryInputVO.setOrgn_deal_coopco_aprv_no(inputVO.getOrgn_deal_coopco_aprv_no());
            oritrqryInputVO.setOrgn_deal_amt(inputVO.getOrgn_deal_amt_pnt());
            oritrqryInputVO.setAns_cd(header.getAns_cd1());

            oritrqryInputWrapperVO = new OritrqryInputWrapperVO();
            oritrqryInputWrapperVO.setHeader(header);
            oritrqryInputWrapperVO.setBody(oritrqryInputVO);
            OritrqryOutputWrapperVO oritrqryOutputVO = oritrqryService.syncCall(oritrqryInputWrapperVO);



            /* 회원 조회 */
            meminfqryInputVO = new MeminfqryInputVO();
            meminfqryInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
            meminfqryInputVO.setCrd_no(inputVO.getTrack_ii_data());
            meminfqryInputVO.setAprv_no("");

            meminfqryInputWrapperVO = new MeminfqryInputWrapperVO();
            meminfqryInputWrapperVO.setHeader(header);
            meminfqryInputWrapperVO.setBody(meminfqryInputVO);
            MeminfqryOutputWrapperVO meminfqryOutputWrapperVO = meminfqryService.syncCall(meminfqryInputWrapperVO);

            /* 취소대상 거래내역 조회 */
            apr_dealtr_trn_tpcom_vf2001InputVO = new Apr_dealtr_trn_tpcom_vf2001InputVO();
            apr_dealtr_trn_tpcom_vf2001InputVO.setMbrsh_pgm_id(oritrqryOutputVO.getBody().getMbrsh_pgm_id());
            apr_dealtr_trn_tpcom_vf2001InputVO.setRep_aprv_no(oritrqryOutputVO.getBody().getRep_aprv_no());
            apr_dealtr_trn_tpcom_vf2001InputVO.setOrgn_aprv_dy(oritrqryOutputVO.getBody().getAprv_dy());
            apr_dealtr_trn_tpcom_vf2001InputVO.setTrc_no(oritrqryOutputVO.getBody().getTrc_no());
            if (CommonFunction.isStringEquals(inputVO.getSlp_cd(), "42")) {
                apr_dealtr_trn_tpcom_vf2001InputVO.setSlp_cd("41");
            } else if (CommonFunction.isStringEquals(inputVO.getSlp_cd(), "22")) {
                apr_dealtr_trn_tpcom_vf2001InputVO.setSlp_cd("11");
            } else {
                throw new ValidException("4468", "취소대상 거래내역 조회 전표 에러");
            }
            apr_dealtr_trn_tpcom_vf2001OutputVO =
                    apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_vf2001(apr_dealtr_trn_tpcom_vf2001InputVO);
            for (Apr_dealtr_trn_tpcom_vf2001OutputVO orgnDealtrOutputVO : apr_dealtr_trn_tpcom_vf2001OutputVO) {
                /* 포인트 갱신(Async 처리로 재개발) */
                mempntuptInputVO = new MempntuptInputVO();
                mempntuptInputVO.setMbrsh_pgm_id(orgnDealtrOutputVO.getMbrsh_pgm_id());
                mempntuptInputVO.setMbr_id(orgnDealtrOutputVO.getMbr_id());
                mempntuptInputVO.setPnt_knd_cd(orgnDealtrOutputVO.getPnt_knd_cd());
                mempntuptInputVO.setOrgan_cd(header.getOrgan_cd());
                mempntuptInputVO.setCur_pnt(orgnDealtrOutputVO.getCur_pnt());
                mempntuptInputVO.setAvl_pnt(orgnDealtrOutputVO.getAvl_pnt());

                mempntuptInputWrapperVO = new MempntuptInputWrapperVO();
                mempntuptInputWrapperVO.setHeader(header);
                mempntuptInputWrapperVO.setBody(mempntuptInputVO);
                MempntuptOutputWrapperVO mempntuptOutputWrapperVO = mempntuptService.syncCall(mempntuptInputWrapperVO);

                /* 취소거래내역 생성 및 원거래 갱신  */
                cntrinsertInputVO = new CntrinsertInputVO();
                cntrinsertInputVO.setMbrsh_pgm_id(orgnDealtrOutputVO.getMbrsh_pgm_id());
                cntrinsertInputVO.setAprv_dy(getaprvnoOutputWrapperVO.getBody().getAprv_dy());
                cntrinsertInputVO.setAprv_no(getaprvnoOutputWrapperVO.getBody().getAprv_no());
                cntrinsertInputVO.setCrd_no(orgnDealtrOutputVO.getCrd_no());
                cntrinsertInputVO.setAprv_tm(getaprvnoOutputWrapperVO.getBody().getAprv_tm());
                cntrinsertInputVO.setDeal_dy(inputVO.getDeal_dy());
                cntrinsertInputVO.setMcht_no(inputVO.getMcht_no());
                cntrinsertInputVO.setIncom_crd_no(orgnDealtrOutputVO.getIncom_crd_no());
                cntrinsertInputVO.setMbr_id(orgnDealtrOutputVO.getMbr_id());
                cntrinsertInputVO.setSlp_cd(inputVO.getSlp_cd());
                cntrinsertInputVO.setDeal_fg(inputVO.getDeal_fg());
                cntrinsertInputVO.setDeal_tm(inputVO.getDeal_tm());
                cntrinsertInputVO.setMix_sttl_yn(inputVO.getMix_sttl_yn());
                cntrinsertInputVO.setMix_sttl_tot_amt(orgnDealtrOutputVO.getMix_sttl_tot_amt());
                cntrinsertInputVO.setSale_qty(orgnDealtrOutputVO.getSale_qty());
                cntrinsertInputVO.setDeal_amt(orgnDealtrOutputVO.getDeal_amt());
                cntrinsertInputVO.setDsc_amt(orgnDealtrOutputVO.getDsc_amt());
                cntrinsertInputVO.setMcht_pnt(orgnDealtrOutputVO.getMcht_pnt());
                cntrinsertInputVO.setCur_pnt(orgnDealtrOutputVO.getCur_pnt());
                cntrinsertInputVO.setAvl_pnt(orgnDealtrOutputVO.getAvl_pnt());
                cntrinsertInputVO.setPnt_knd_cd(orgnDealtrOutputVO.getPnt_knd_cd());
                cntrinsertInputVO.setAnnfee(orgnDealtrOutputVO.getAnnfee());
                cntrinsertInputVO.setMbrsh_svc_annfee(orgnDealtrOutputVO.getMbrsh_svc_annfee());
                cntrinsertInputVO.setRefu_lmt_inc_amt(orgnDealtrOutputVO.getRefu_lmt_inc_amt());
                cntrinsertInputVO.setDeal_bef_cur_pnt(0L);
                cntrinsertInputVO.setDeal_bef_avl_pnt(0L);
                cntrinsertInputVO.setDeal_bef_annfee(0L);
                cntrinsertInputVO.setDeal_bef_mbrsh_svc_annfee(0L);
                cntrinsertInputVO.setCncl_typ("C");
                cntrinsertInputVO.setCs_slp_cd(orgnDealtrOutputVO.getCs_slp_cd());
                cntrinsertInputVO.setMbrsh_svc_fg(orgnDealtrOutputVO.getMbrsh_svc_fg());
                cntrinsertInputVO.setDir_self_mng_fg(orgnDealtrOutputVO.getDir_self_mng_fg());
                cntrinsertInputVO.setIncom_organ_cd(orgnDealtrOutputVO.getIncom_organ_cd());
                cntrinsertInputVO.setTer_fg(inputVO.getTer_fg());
                cntrinsertInputVO.setTelgrm_no(header.getTelgrm_no());
                cntrinsertInputVO.setTrc_no(header.getTrc_no());
                cntrinsertInputVO.setOrgn_aprv_dy(orgnDealtrOutputVO.getAprv_dy());
                cntrinsertInputVO.setOrgn_aprv_no(orgnDealtrOutputVO.getAprv_no());
                cntrinsertInputVO.setOrgn_deal_dy(orgnDealtrOutputVO.getDeal_dy());
                cntrinsertInputVO.setRep_aprv_no(getaprvnoOutputWrapperVO.getBody().getRep_aprv_no());
                cntrinsertInputVO.setSttl_mcht_no(orgnDealtrOutputVO.getSttl_mcht_no());
                cntrinsertInputVO.setSttl_amt(orgnDealtrOutputVO.getSttl_amt());
                cntrinsertInputVO.setSk_chrg_amt(orgnDealtrOutputVO.getSk_chrg_amt());
                cntrinsertInputVO.setContr_fg_cd(orgnDealtrOutputVO.getContr_fg_cd());
                cntrinsertInputVO.setSvc_grp_no(orgnDealtrOutputVO.getSvc_grp_no());
                cntrinsertInputVO.setCpn_prd_cd(orgnDealtrOutputVO.getCpn_prd_cd());
                cntrinsertInputVO.setCpn_prd_qty(orgnDealtrOutputVO.getCpn_prd_qty());
                cntrinsertInputVO.setDist_cust_cpn_mng_no(orgnDealtrOutputVO.getDist_cust_cpn_mng_no());
                cntrinsertInputVO.setClltbrd_no(orgnDealtrOutputVO.getClltbrd_no());
                cntrinsertInputVO.setCoopco_cd(orgnDealtrOutputVO.getCoopco_cd());
                cntrinsertInputVO.setSttl_coopco_cd(orgnDealtrOutputVO.getSttl_coopco_cd());
                cntrinsertInputVO.setMngco_chrg_sttl_mcht_no(orgnDealtrOutputVO.getMngco_chrg_sttl_mcht_no());
                cntrinsertInputVO.setCoop_crd_cd(orgnDealtrOutputVO.getCoop_crd_cd());
                cntrinsertInputVO.setVat_incld_yn(orgnDealtrOutputVO.getVat_incld_yn());
                cntrinsertInputVO.setFee_apl_bas_fg(orgnDealtrOutputVO.getFee_apl_bas_fg());
                cntrinsertInputVO.setFee_rt(orgnDealtrOutputVO.getFee_rt());
                cntrinsertInputVO.setFee_amt(orgnDealtrOutputVO.getFee_amt());
                cntrinsertInputVO.setApl_rt(orgnDealtrOutputVO.getApl_rt());
                cntrinsertInputVO.setCash_arcpt_issu_yn(orgnDealtrOutputVO.getCash_arcpt_issu_yn());
                cntrinsertInputVO.setChip_deal_yn(orgnDealtrOutputVO.getChip_deal_yn());
                cntrinsertInputVO.setCoopco_aprv_no(orgnDealtrOutputVO.getCoopco_aprv_no());
                cntrinsertInputVO.setCoopco_mbr_id(inputVO.getCoopco_mbr_id());
                cntrinsertInputVO.setCoopco_mbr_ip_addr(inputVO.getCoopco_mbr_ip_addr());
                cntrinsertInputVO.setBat_file_nm(inputVO.getBat_file_nm());
                cntrinsertInputVO.setTelgrm_fg(header.getTelgrm_fg());
                cntrinsertInputVO.setDeal_caus_cd(inputVO.getDeal_caus_cd());
                cntrinsertInputVO.setSvc_modu_id("ZPTUTXPTC0001");
                cntrinsertInputVO.setHyb_contr_id(orgnDealtrOutputVO.getHyb_contr_id());
                if (CommonFunction.isAllStringNotEquals(header.getAns_cd1(), "30", "50", "60"))  {
                    cntrinsertInputVO.setAns_cd("00");
                } else {
                    cntrinsertInputVO.setAns_cd(header.getAns_cd1());
                }

                cntrinsertInputWrapperVO = new CntrinsertInputWrapperVO();
                cntrinsertInputWrapperVO.setHeader(header);
                cntrinsertInputWrapperVO.setBody(cntrinsertInputVO);
                CntrinsertOutputWrapperVO cntrinsertOutputWrapperVO = cntrinsertService.syncCall(cntrinsertInputWrapperVO);
            }

            outputVO = new ZptutxptcOutputVO();
            outputVO.setMbrsh_pgm_id("A");
            outputVO.setAprv_dy("20181219");
            outputVO.setAprv_no("F88888888");
            outputVO.setMcht_no("123456789");
            outputVO.setCrd_no("2222222222222222");

            outputVOList = new ArrayList<ZptutxptcOutputVO>();
            outputVOList.add(outputVO);
        }

        // 결과값 header setting
        outputWrapperVO.setHeader(
                new CustomizeHeaderVO(
                        inputWrapperVO.getHeader().getTelgrm_no().substring(0, 3).concat("1"),
                        inputWrapperVO.getHeader().getOrgan_cd(),
                        new SimpleDateFormat("yyyyMMdd").format(new Date()),
                        new SimpleDateFormat("HHmmss").format(new Date()),
                        inputWrapperVO.getHeader().getTrc_no(),
                        inputWrapperVO.getHeader().getTelgrm_fg(),
                        "00",
                        "00",
                        ""));
        outputWrapperVO.setBody(outputVOList);
        outputWrapperVO.setTotalBodyCnt(bodyCount);

        return outputWrapperVO;

//    List<ZptutxptcOutputVO> outputVOList = new ArrayList<ZptutxptcOutputVO>();
//    outputVO =new
//
//    ZptutxptcOutputVO();
//    outputVO.setMbrsh_pgm_id("A");
//    outputVO.setAprv_no("F88888888");
//    outputVO.setAprv_dy("20190228");
//    outputVO.setCrd_no("1111111111111111");
//    outputVO.setMcht_no("22222222");
//    outputVO.setMbr_id("333333333");
//    outputVOList.add(outputVO);
//
//    outputWrapperVO.setBody(outputVOList);
//    outputWrapperVO.setTotalBodyCnt(1);
//    return outputWrapperVO;
}


//    public ResponseEntity<ZptutxptcOutputVO> syncCall(HttpHeaders requestHeader,
//                                                      ZptutxptcInputVO inputVO) {
//
//        Map<String, String> header = requestHeader.toSingleValueMap();
//        /* 전문 유효성 체크 */
//        onmsgchkInputVO = new OnmsgchkInputVO();
//        onmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
//        onmsgchkInputVO.setTelgrm_no(header.get("telgrm_no"));
//        onmsgchkInputVO.setOrgan_cd(header.get("organ_cd"));
//        onmsgchkInputVO.setTrc_no(header.get("trc_no"));
//        onmsgchkInputVO.setMcht_no(inputVO.getMcht_no());
//        onmsgchkInputVO.setMcht_biz_no("");
//        onmsgchkInputVO.setDeal_dy(inputVO.getDeal_dy());
//        onmsgchkInputVO.setCrd_no(inputVO.getTrack_ii_data());
//        onmsgchkInputVO.setResd_no(inputVO.getResd_no());
//        onmsgchkInputVO.setOrgn_deal_dy(inputVO.getOrgn_deal_dy());
//        onmsgchkInputVO.setOrgn_deal_aprv_no(inputVO.getOrgn_deal_aprv_no());
//        onmsgchkInputVO.setOrgn_deal_coopco_aprv_no(inputVO.getOrgn_deal_coopco_aprv_no());
//        onmsgchkInputVO.setCncl_req_fg(inputVO.getCash_arcpt_proc_fg());
//        onmsgchkInputVO.setDeal_amt_sum(0L);
//        onmsgchkInputVO.setDeal_amt1(0L);
//        onmsgchkInputVO.setOrgn_deal_amt(0L);
//        ResponseEntity<OnmsgchkOutputVO> onmsgchkOutputVO = onmsgchkService.syncCall(requestHeader, onmsgchkInputVO);
//
//
//        /* 승인번호 채번 */
//        getaprvnoInputVO = new GetaprvnoInputVO();
//        getaprvnoInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
//        getaprvnoInputVO.setSvc_modu_id("ZPTUTXPTC0001");
//        getaprvnoInputVO.setMcht_no(inputVO.getMcht_no());
//        getaprvnoInputVO.setTelgrm_fg(header.get("telgrm_fg"));
//        ResponseEntity<GetaprvnoOutputVO> getaprvnoOutputVO = getaprvnoService.syncCall(requestHeader, getaprvnoInputVO);
//
//
//        /* 원거래 조회 */
//        oritrqryInputVO = new OritrqryInputVO();
//        oritrqryInputVO.setSvc_modu_id("ZPTUTXPTC0001");
//        oritrqryInputVO.setTrc_no(header.get("trc_no"));
//        oritrqryInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
//        oritrqryInputVO.setMcht_no(inputVO.getMcht_no());
//        oritrqryInputVO.setCrd_no(inputVO.getTrack_ii_data());
//        oritrqryInputVO.setOrgn_Deal_dy(inputVO.getOrgn_deal_dy());
//        oritrqryInputVO.setOrgn_deal_aprv_no(inputVO.getOrgn_deal_aprv_no());
//        oritrqryInputVO.setOrgn_deal_coopco_aprv_no(inputVO.getOrgn_deal_coopco_aprv_no());
//        oritrqryInputVO.setOrgn_deal_amt(inputVO.getOrgn_deal_amt_pnt());
//        oritrqryInputVO.setAns_cd(header.get("ans_cd1"));
//        ResponseEntity<OritrqryOutputVO> oritrqryOutputVO = oritrqryService.syncCall(requestHeader, oritrqryInputVO);
//
//
//        /* 회원 조회 */
//        meminfqryInputVO = new MeminfqryInputVO();
//        meminfqryInputVO.setMbrsh_pgm_id(inputVO.getMbrsh_pgm_id());
//        meminfqryInputVO.setCrd_no(inputVO.getTrack_ii_data());
//        meminfqryInputVO.setAprv_no("");
//        ResponseEntity<MeminfqryOutputVO> meminfqryOutputVO = meminfqryService.syncCall(requestHeader, meminfqryInputVO);
//
//        /* 취소대상 거래내역 조회 */
//        apr_dealtr_trn_tpcom_vf2001InputVO = new Apr_dealtr_trn_tpcom_vf2001InputVO();
//        apr_dealtr_trn_tpcom_vf2001InputVO.setMbrsh_pgm_id(oritrqryOutputVO.getBody().getMbrsh_pgm_id());
//        apr_dealtr_trn_tpcom_vf2001InputVO.setRep_aprv_no(oritrqryOutputVO.getBody().getRep_aprv_no());
//        apr_dealtr_trn_tpcom_vf2001InputVO.setOrgn_aprv_dy(oritrqryOutputVO.getBody().getAprv_dy());
//        apr_dealtr_trn_tpcom_vf2001InputVO.setTrc_no(oritrqryOutputVO.getBody().getTrc_no());
//        if (!StringUtils.isEmpty(inputVO.getSlp_cd()) && inputVO.getSlp_cd().equals("42")) {
//            apr_dealtr_trn_tpcom_vf2001InputVO.setSlp_cd("41");
//        } else if (!StringUtils.isEmpty(inputVO.getSlp_cd()) && inputVO.getSlp_cd().equals("22")) {
//            apr_dealtr_trn_tpcom_vf2001InputVO.setSlp_cd("11");
//        } else {
//            throw new ValidException(requestHeader, "4468", "취소대상 거래내역 조회 전표 에러");
//        }
//        apr_dealtr_trn_tpcom_vf2001OutputVO =
//                apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_vf2001(apr_dealtr_trn_tpcom_vf2001InputVO);
//        for (Apr_dealtr_trn_tpcom_vf2001OutputVO orgnDealtrOutputVO : apr_dealtr_trn_tpcom_vf2001OutputVO) {
//            /* 포인트 갱신(Async 처리로 재개발) 필요 */
//            mempntuptInputVO = new MempntuptInputVO();
//            mempntuptInputVO.setMbrsh_pgm_id(orgnDealtrOutputVO.getMbrsh_pgm_id());
//            mempntuptInputVO.setMbr_id(orgnDealtrOutputVO.getMbr_id());
//            mempntuptInputVO.setPnt_knd_cd(orgnDealtrOutputVO.getPnt_knd_cd());
//            mempntuptInputVO.setOrgan_cd(header.get("organ_cd"));
//            mempntuptInputVO.setCur_pnt(orgnDealtrOutputVO.getCur_pnt());
//            mempntuptInputVO.setAvl_pnt(orgnDealtrOutputVO.getAvl_pnt());
//            ResponseEntity<MempntuptOutputVO> mempntuptOutputVO = mempntuptService.syncCall(requestHeader, mempntuptInputVO);
//
//            /* 취소거래내역 생성 및 원거래 갱신  */
//            cntrinsertInputVO = new CntrinsertInputVO();
//            cntrinsertInputVO.setMbrsh_pgm_id(orgnDealtrOutputVO.getMbrsh_pgm_id());
//            cntrinsertInputVO.setAprv_dy(getaprvnoOutputVO.getBody().getAprv_dy());
//            cntrinsertInputVO.setAprv_no(getaprvnoOutputVO.getBody().getAprv_no());
//            cntrinsertInputVO.setCrd_no(orgnDealtrOutputVO.getCrd_no());
//            cntrinsertInputVO.setAprv_tm(getaprvnoOutputVO.getBody().getAprv_tm());
//            cntrinsertInputVO.setDeal_dy(inputVO.getDeal_dy());
//            cntrinsertInputVO.setMcht_no(inputVO.getMcht_no());
//            cntrinsertInputVO.setIncom_crd_no(orgnDealtrOutputVO.getIncom_crd_no());
//            cntrinsertInputVO.setMbr_id(orgnDealtrOutputVO.getMbr_id());
//            cntrinsertInputVO.setSlp_cd(inputVO.getSlp_cd());
//            cntrinsertInputVO.setDeal_fg(inputVO.getDeal_fg());
//            cntrinsertInputVO.setDeal_tm(inputVO.getDeal_tm());
//            cntrinsertInputVO.setMix_sttl_yn(inputVO.getMix_sttl_yn());
//            cntrinsertInputVO.setMix_sttl_tot_amt(orgnDealtrOutputVO.getMix_sttl_tot_amt());
//            cntrinsertInputVO.setSale_qty(orgnDealtrOutputVO.getSale_qty());
//            cntrinsertInputVO.setDeal_amt(orgnDealtrOutputVO.getDeal_amt());
//            cntrinsertInputVO.setDsc_amt(orgnDealtrOutputVO.getDsc_amt());
//            cntrinsertInputVO.setMcht_pnt(orgnDealtrOutputVO.getMcht_pnt());
//            cntrinsertInputVO.setCur_pnt(orgnDealtrOutputVO.getCur_pnt());
//            cntrinsertInputVO.setAvl_pnt(orgnDealtrOutputVO.getAvl_pnt());
//            cntrinsertInputVO.setPnt_knd_cd(orgnDealtrOutputVO.getPnt_knd_cd());
//            cntrinsertInputVO.setAnnfee(orgnDealtrOutputVO.getAnnfee());
//            cntrinsertInputVO.setMbrsh_svc_annfee(orgnDealtrOutputVO.getMbrsh_svc_annfee());
//            cntrinsertInputVO.setRefu_lmt_inc_amt(orgnDealtrOutputVO.getRefu_lmt_inc_amt());
//            cntrinsertInputVO.setDeal_bef_cur_pnt(0L);
//            cntrinsertInputVO.setDeal_bef_avl_pnt(0L);
//            cntrinsertInputVO.setDeal_bef_annfee(0L);
//            cntrinsertInputVO.setDeal_bef_mbrsh_svc_annfee(0L);
//            cntrinsertInputVO.setCncl_typ("C");
//            cntrinsertInputVO.setCs_slp_cd(orgnDealtrOutputVO.getCs_slp_cd());
//            cntrinsertInputVO.setMbrsh_svc_fg(orgnDealtrOutputVO.getMbrsh_svc_fg());
//            cntrinsertInputVO.setDir_self_mng_fg(orgnDealtrOutputVO.getDir_self_mng_fg());
//            cntrinsertInputVO.setIncom_organ_cd(orgnDealtrOutputVO.getIncom_organ_cd());
//            cntrinsertInputVO.setTer_fg(inputVO.getTer_fg());
//            cntrinsertInputVO.setTelgrm_no(header.get("telgrm_no"));
//            cntrinsertInputVO.setTrc_no(header.get("trc_no"));
//            cntrinsertInputVO.setOrgn_aprv_dy(orgnDealtrOutputVO.getAprv_dy());
//            cntrinsertInputVO.setOrgn_aprv_no(orgnDealtrOutputVO.getAprv_no());
//            cntrinsertInputVO.setOrgn_deal_dy(orgnDealtrOutputVO.getDeal_dy());
//            cntrinsertInputVO.setRep_aprv_no(getaprvnoOutputVO.getBody().getRep_aprv_no());
//            cntrinsertInputVO.setSttl_mcht_no(orgnDealtrOutputVO.getSttl_mcht_no());
//            cntrinsertInputVO.setSttl_amt(orgnDealtrOutputVO.getSttl_amt());
//            cntrinsertInputVO.setSk_chrg_amt(orgnDealtrOutputVO.getSk_chrg_amt());
//            cntrinsertInputVO.setContr_fg_cd(orgnDealtrOutputVO.getContr_fg_cd());
//            cntrinsertInputVO.setSvc_grp_no(orgnDealtrOutputVO.getSvc_grp_no());
//            cntrinsertInputVO.setCpn_prd_cd(orgnDealtrOutputVO.getCpn_prd_cd());
//            cntrinsertInputVO.setCpn_prd_qty(orgnDealtrOutputVO.getCpn_prd_qty());
//            cntrinsertInputVO.setDist_cust_cpn_mng_no(orgnDealtrOutputVO.getDist_cust_cpn_mng_no());
//            cntrinsertInputVO.setClltbrd_no(orgnDealtrOutputVO.getClltbrd_no());
//            cntrinsertInputVO.setCoopco_cd(orgnDealtrOutputVO.getCoopco_cd());
//            cntrinsertInputVO.setSttl_coopco_cd(orgnDealtrOutputVO.getSttl_coopco_cd());
//            cntrinsertInputVO.setMngco_chrg_sttl_mcht_no(orgnDealtrOutputVO.getMngco_chrg_sttl_mcht_no());
//            cntrinsertInputVO.setCoop_crd_cd(orgnDealtrOutputVO.getCoop_crd_cd());
//            cntrinsertInputVO.setVat_incld_yn(orgnDealtrOutputVO.getVat_incld_yn());
//            cntrinsertInputVO.setFee_apl_bas_fg(orgnDealtrOutputVO.getFee_apl_bas_fg());
//            cntrinsertInputVO.setFee_rt(orgnDealtrOutputVO.getFee_rt());
//            cntrinsertInputVO.setFee_amt(orgnDealtrOutputVO.getFee_amt());
//            cntrinsertInputVO.setApl_rt(orgnDealtrOutputVO.getApl_rt());
//            cntrinsertInputVO.setCash_arcpt_issu_yn(orgnDealtrOutputVO.getCash_arcpt_issu_yn());
//            cntrinsertInputVO.setChip_deal_yn(orgnDealtrOutputVO.getChip_deal_yn());
//            cntrinsertInputVO.setCoopco_aprv_no(orgnDealtrOutputVO.getCoopco_aprv_no());
//            cntrinsertInputVO.setCoopco_mbr_id(inputVO.getCoopco_mbr_id());
//            cntrinsertInputVO.setCoopco_mbr_ip_addr(inputVO.getCoopco_mbr_ip_addr());
//            cntrinsertInputVO.setBat_file_nm(inputVO.getBat_file_nm());
//            cntrinsertInputVO.setTelgrm_fg(header.get("telgrm_fg"));
//            cntrinsertInputVO.setDeal_caus_cd(inputVO.getDeal_caus_cd());
//            cntrinsertInputVO.setSvc_modu_id("ZPTUTXPTC0001");
//            cntrinsertInputVO.setHyb_contr_id(orgnDealtrOutputVO.getHyb_contr_id());
//            if (!StringUtils.isEmpty(header.get("ans_cd")) &&
//                    (!header.get("ans_cd").equals("30") &&
//                            !header.get("ans_cd").equals("50") &&
//                            !header.get("ans_cd").equals("60"))) {
//                cntrinsertInputVO.setAns_cd("00");
//            } else {
//                cntrinsertInputVO.setAns_cd(header.get("ans_cd"));
//            }
//            ResponseEntity<CntrinsertOutputVO> cntrinsertOutputVO = cntrinsertService.syncCall(requestHeader, cntrinsertInputVO);
//
//
//        }
//
//        // 결과값 header setting
//        responseHeaders = new HttpHeaders();
//        responseHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
//        responseHeaders.add("telgrm_no", header.get("telgrm_no").substring(0,3).concat("1"));
//        responseHeaders.add("organ_cd", header.get("organ_cd"));
//        responseHeaders.add("send_dy", new SimpleDateFormat("yyyyMMdd").format(System.currentTimeMillis()));
//        responseHeaders.add("send_tm", new SimpleDateFormat("HHmmss").format(System.currentTimeMillis()));
//        responseHeaders.add("trc_no", header.get("trc_no"));
//        responseHeaders.add("telgrm_fg", header.get("telgrm_fg"));
//        responseHeaders.add("data_size", header.get("data_size"));
//        responseHeaders.add("ans_cd1", "00");
//        responseHeaders.add("ans_cd2", "00");
//        responseHeaders.add("filler", "");
//
//        outputVO = new ZptutxptcOutputVO();
//        outputVO.setMbrsh_pgm_id("A");
//        outputVO.setAprv_dy("20181219");
//        outputVO.setAprv_no("F88888888");
//        outputVO.setMcht_no("123456789");
//        outputVO.setCrd_no("2222222222222222");
//
//
//        return new ResponseEntity<ZptutxptcOutputVO>(outputVO, responseHeaders, HttpStatus.OK);
//    }
}
