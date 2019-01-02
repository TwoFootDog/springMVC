package com.tpcom_apr.service;

import com.commons.exception.ValidException;
import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import com.tpcom_apr.domain.Rul_svcavl_con_tpcom_vs2001InputVO;
import com.tpcom_apr.domain.Rul_svcavl_con_tpcom_vs2001OutputVO;
import com.tpcom_apr.mapper.Rul_svcavl_conMapper;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;

@Service
@Log4j
//@AllArgsConstructor
public class OnmsgchkServiceImpl implements OnmsgchkService {
    @Setter(onMethod_ = {@Autowired})
    private Rul_svcavl_conMapper rul_svcavl_conMapper;
    private Rul_svcavl_con_tpcom_vs2001OutputVO rul_svcavl_con_tpcom_vs2001OutputVO;

    private HttpHeaders responseHeader;
    private OnmsgchkOutputVO outputVO;


    public ResponseEntity<OnmsgchkOutputVO> syncCall(HttpServletRequest request, OnmsgchkInputVO inputVO) {

        /* 공통 입력값 유효성 체크 */
//        commonInputDataValidChk(request, inputVO);

        /* rul_svcavl_con_tpcom_vs2001(서비스모듈 유효성 확인조회 SQL 호출하여 결과값 셋팅 */
         rul_svcavl_con_tpcom_vs2001OutputVO =
                rul_svcavl_conMapper.rul_svcavl_con_tpcom_vs2001(
                        new Rul_svcavl_con_tpcom_vs2001InputVO(
                                inputVO.getOrgan_cd(),
                                inputVO.getTelgrm_no(),
                                inputVO.getSvc_modu_id(),
                                inputVO.getTelgrm_fg()));


         /* rul_svcavl_con_tpcom_vs2001 결과값 존재 시 OnmsgchkOutputVO(온라인 전문 유효성 체크) output 값 셋팅. 미 존재 시 7777 에러 발생 */
        if (!StringUtils.isEmpty(rul_svcavl_con_tpcom_vs2001OutputVO)) {
            responseHeader = new HttpHeaders();
            responseHeader.add("ans_cd", "0000");
            outputVO = new OnmsgchkOutputVO(
                    rul_svcavl_con_tpcom_vs2001OutputVO.getMbrsh_pgm_id(),
                    rul_svcavl_con_tpcom_vs2001OutputVO.getTelgrm_typ(),
                    "0000",
                    rul_svcavl_con_tpcom_vs2001OutputVO.getMsg_fg()
            );
        } else if (StringUtils.isEmpty(rul_svcavl_con_tpcom_vs2001OutputVO)){   // 쿼리 처리 결과 미 존재 시
            throw new ValidException("7777", "데이터 미 존재");
        } else {
            throw new ValidException("9080", "시스템실 연락바람");
        }

        /* 취소전문이 아닌경우와 취소전문인 경우를 구분하여 필수값 체크 */
        if (rul_svcavl_con_tpcom_vs2001OutputVO.getCncl_yn().equals("N")) {     // 취소 전문이 아닌 경우
            if (inputVO.getMcht_biz_no().length() != 10) {
                throw new ValidException("3311", "사업자번호 미입력 또는 길이 오류 [" + inputVO.getMcht_biz_no() + "]");
            }
        } else {    // 취소 전문인 경우
            if (StringUtils.isEmpty(inputVO.getOrgn_deal_dy())) {
                throw new ValidException("7745", "원거래일자 미입력");
            }
            if (StringUtils.isEmpty(inputVO.getOrgn_deal_aprv_no()) &&
                    StringUtils.isEmpty(inputVO.getOrgn_deal_coopco_aprv_no()) &&
                    !inputVO.getAns_cd1().equals("60") &&
                    !inputVO.getAns_cd1().equals("52")) {
                throw new ValidException("7746", "원거래승인번호/원거래제휴사승인번호 미입력");
            }
            if (StringUtils.isEmpty(inputVO.getCncl_req_fg())) {
                throw new ValidException("3313", "요청 서비스구분 미입력");
            }
        }

        return new ResponseEntity<OnmsgchkOutputVO>(outputVO, responseHeader, HttpStatus.OK);
    }

    /* 공통 입력값 유효성 체크*/
    public void commonInputDataValidChk(HttpServletRequest request, OnmsgchkInputVO inputVO) {
        if (StringUtils.isEmpty(request.getHeader("trc_no"))) {
            throw new ValidException("7777", "전문번호 미 유입");
        }
        if (StringUtils.isEmpty(inputVO.getMcht_no())) {
            throw new ValidException("7730", "가맹점번호 미 유입");
        }
        if (StringUtils.isEmpty(inputVO.getDeal_dy())) {
            throw new ValidException("6940", "거래일자 미 유입");
        }
        if (StringUtils.isEmpty(inputVO.getCrd_no()) &&
                StringUtils.isEmpty(inputVO.getResd_no())) {
            throw new ValidException("7720", "식별자 미 유입");
        }
    }
}
