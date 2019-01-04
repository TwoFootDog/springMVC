package com.tpcom_apr.service;

import com.commons.exception.ValidException;
import com.tpcom_apr.domain.*;
import com.tpcom_apr.mapper.Apr_dealtr_trnMapper;
import com.tpcom_apr.service.service_interface.OritrqryService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

public class OritqryServiceImpl implements OritrqryService {

    @Setter(onMethod_ = {@Autowired})
    private Apr_dealtr_trnMapper apr_dealtr_trnMapper;

    private Apr_dealtr_trn_tpcom_vs2001OutputVO apr_dealtr_trn_tpcom_vs2001OutputVO;
    private Apr_dealtr_trn_tpcom_vs2002OutputVO apr_dealtr_trn_tpcom_vs2002OutputVO;
    private Apr_dealtr_trn_tpcom_vs2003OutputVO apr_dealtr_trn_tpcom_vs2003OutputVO;
    private Apr_dealtr_trn_tpcom_vs2004OutputVO apr_dealtr_trn_tpcom_vs2004OutputVO;
    private Apr_dealtr_trn_tpcom_vs2035OutputVO apr_dealtr_trn_tpcom_vs2035OutputVO;

    private HttpHeaders responseHeader;
    private OritrqryOutputVO outputVO;


    @Override
    public ResponseEntity<OritrqryOutputVO> syncCall(HttpServletRequest request, OritrqryInputVO inputVO) {

        String orgn_deal_aprv_no;
        String orgn_deal_coopco_aprv_no;

        int sql_type = sqlTypeSetting(inputVO);

        Map<String, String> aprvNo = changeOrgnAprvNo(inputVO.getOrgn_deal_aprv_no(), inputVO.getOrgn_deal_coopco_aprv_no());
        orgn_deal_aprv_no = aprvNo.get("orgn_deal_aprv_no");
        orgn_deal_coopco_aprv_no = aprvNo.get("orgn_deal_coopco_aprv_no");

        switch (sql_type) {
            case 11 :
                // 일반적립취소
                apr_dealtr_trn_tpcom_vs2001OutputVO =
                        apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_vs2001(
                                new Apr_dealtr_trn_tpcom_vs2001InputVO(
                                        inputVO.getMbrsh_pgm_id(),
                                        inputVO.getOrgn_Deal_dy(),
                                        inputVO.getCrd_no(),
                                        inputVO.getMcht_no(),
                                        inputVO.getMix_sttl_yn(),
                                        inputVO.getSvc_modu_id(),
                                        inputVO.getOrgn_deal_amt(),
                                        inputVO.getOrgn_deal_aprv_no(),
                                        inputVO.getOrgn_deal_coopco_aprv_no()));
                break;
            case 21 :
                // 일반사용취소
                apr_dealtr_trn_tpcom_vs2002OutputVO =
                        apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_vs2002(
                                new Apr_dealtr_trn_tpcom_vs2002InputVO(
                                        inputVO.getMbrsh_pgm_id(),
                                        inputVO.getOrgn_Deal_dy(),
                                        inputVO.getCrd_no(),
                                        inputVO.getMcht_no(),
                                        inputVO.getOrgn_deal_coopco_aprv_no(),
                                        inputVO.getOrgn_deal_aprv_no(),
                                        inputVO.getOrgn_deal_amt(),
                                        request.getHeader("organ_cd"), inputVO.getSvc_modu_id()));
                break;
            case 10 :
                // 망상적립취소
                break;
            case 20 :
                // 망상사용취소
                break;
            case 22 :
                // 망상재사용
                break;
            default :
                throw new ValidException("9080", "원거래조회 처리 유형 에러");
        }


        return new ResponseEntity<OritrqryOutputVO>(outputVO, responseHeader, HttpStatus.OK);
    }



    private int sqlTypeSetting(OritrqryInputVO inputVO) {
        int caller_type;
        int cancel_type;
        int sql_type;

        if ("ZPTOTXPTC0001".equals(inputVO.getSvc_modu_id()) ||
                "ZRCPTXPTC0001".equals(inputVO.getSvc_modu_id()) ||
                "ZSPOTXPTC0001".equals(inputVO.getSvc_modu_id()) ||
                "ZEBCTNPTOC001".equals(inputVO.getSvc_modu_id()) ||
                "ZCPNTXDSC0001".equals(inputVO.getSvc_modu_id())) {

            caller_type = 1;    // 적립성 서비스 취소

        } else if ("ZPTUTXPTC0001".equals(inputVO.getSvc_modu_id()) ||
                "ZGFTTXPTC0001".equals(inputVO.getSvc_modu_id()) ||
                "ZDSCTXALC0001".equals(inputVO.getSvc_modu_id()) ||
                "ZDSCTXPTC0001".equals(inputVO.getSvc_modu_id()) ||
                "ZEBCTNPTU0001".equals(inputVO.getSvc_modu_id()) ||
                "ZJONTNPTU0001".equals(inputVO.getSvc_modu_id())) {

            caller_type = 2;    // 사용성 서비스 취소

        } else {

            throw new ValidException("9080", "요청 서비스ID로는 처리할 수 없습니다.");

        }

        if ("60".equals(inputVO.getAns_cd()) ||
                "52".equals(inputVO.getAns_cd())) {

            cancel_type = 0;    // 망상취소

        } else if ("70".equals(inputVO.getAns_cd())){

            cancel_type = 2;    // 망상재사용

        } else {

            cancel_type = 1;    // 일반취소

        }

        sql_type = caller_type * 10 + cancel_type;

        return sql_type;
    }


    private Map<String, String> changeOrgnAprvNo(String orgn_deal_aprv_no, String orgn_deal_coopco_aprv_no) {

        Map<String, String> aprvNo = new HashMap<>();

        if (!StringUtils.isEmpty(orgn_deal_coopco_aprv_no)) {
            if (orgn_deal_coopco_aprv_no.length() == 9 && (
                    "51".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "52".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "06".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "20".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "21".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "22".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "23".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "G1".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "G2".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "F8".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "69".equals(orgn_deal_coopco_aprv_no.substring(1, 2)) ||
                    "25".equals(orgn_deal_coopco_aprv_no.substring(1, 2)))) {

                aprvNo.put("orgn_deal_aprv_no", orgn_deal_coopco_aprv_no);

            } else {

                aprvNo.put("orgn_deal_coopco_aprv_no", orgn_deal_coopco_aprv_no);

            }
        } else {
            if (!StringUtils.isEmpty(orgn_deal_aprv_no) && orgn_deal_aprv_no.length() == 9) {
                if (orgn_deal_aprv_no.substring(1, 2).equals("51") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                        orgn_deal_aprv_no.substring(1, 2).equals("52") ||
                )
            }

        }
        return aprvNo;
    }



}
