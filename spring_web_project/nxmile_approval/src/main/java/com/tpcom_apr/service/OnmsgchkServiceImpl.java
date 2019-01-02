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


    public ResponseEntity<OnmsgchkOutputVO> syncCall(HttpServletRequest request, OnmsgchkInputVO onmsgchkInputVO) {

        telgrmValidChk(request, onmsgchkInputVO); // 입력값 검증

             rul_svcavl_con_tpcom_vs2001OutputVO =
                    rul_svcavl_conMapper.rul_svcavl_con_tpcom_vs2001(
                            new Rul_svcavl_con_tpcom_vs2001InputVO(
                                    onmsgchkInputVO.getOrgan_cd(),
                                    onmsgchkInputVO.getTelgrm_no(),
                                    onmsgchkInputVO.getSvc_modu_id(),
                                    onmsgchkInputVO.getTelgrm_fg()));


        if (!StringUtils.isEmpty(rul_svcavl_con_tpcom_vs2001OutputVO)) {
            responseHeader = new HttpHeaders();                             // 해더 생성
            responseHeader.add("ans_cd", "0000");   // 해더에 응답코드 셋팅
            outputVO = new OnmsgchkOutputVO(                                // output 생성
                    rul_svcavl_con_tpcom_vs2001OutputVO.getMbrsh_pgm_id(),
                    rul_svcavl_con_tpcom_vs2001OutputVO.getTelgrm_typ(),
                    "0000",
                    rul_svcavl_con_tpcom_vs2001OutputVO.getMsg_fg()
            );

        } else if (StringUtils.isEmpty(rul_svcavl_con_tpcom_vs2001OutputVO)){
            throw new ValidException("7777", "데이터 미 존재");
        } else {
            throw new ValidException("9080", "시스템실 연락바람");
        }

        return new ResponseEntity<OnmsgchkOutputVO>(outputVO, responseHeader, HttpStatus.OK);
    }

    public void telgrmValidChk(HttpServletRequest request, OnmsgchkInputVO onmsgchkInputVO) {
        if (StringUtils.isEmpty(request.getHeader("trc_no"))) {
            throw new ValidException("7777", "전문번호 미 유입");
        }

        if (StringUtils.isEmpty(onmsgchkInputVO.getMcht_no())) {
            throw new ValidException("7730", "가맹점번호 미 유입");
        }

        if (StringUtils.isEmpty(onmsgchkInputVO.getDeal_dy())) {
            throw new ValidException("6940", "거래일자 미 유입");
        }

        if (StringUtils.isEmpty(onmsgchkInputVO.getCrd_no()) &&
                StringUtils.isEmpty(onmsgchkInputVO.getResd_no())) {
            throw new ValidException("7720", "식별자 미 유입");
        }
    }
}
