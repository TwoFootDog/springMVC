package com.tpcom_apr.service;

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

         responseHeader = new HttpHeaders();

             rul_svcavl_con_tpcom_vs2001OutputVO =
                    rul_svcavl_conMapper.rul_svcavl_con_tpcom_vs2001(
                            new Rul_svcavl_con_tpcom_vs2001InputVO(
                                    onmsgchkInputVO.getOrgan_cd(),
                                    onmsgchkInputVO.getTelgrm_no(),
                                    onmsgchkInputVO.getSvc_modu_id(),
                                    onmsgchkInputVO.getTelgrm_fg()));

        if (StringUtils.isEmpty(rul_svcavl_con_tpcom_vs2001OutputVO)) {
            responseHeader.set("ans_cd", "7777");
        }else {
            responseHeader.set("ans_cd", "0000");
            outputVO = new OnmsgchkOutputVO(
                    rul_svcavl_con_tpcom_vs2001OutputVO.getMbrsh_pgm_id(),
                    rul_svcavl_con_tpcom_vs2001OutputVO.getTelgrm_typ(),
                    "0000",
                    rul_svcavl_con_tpcom_vs2001OutputVO.getMsg_fg()
            );
        }

        return new ResponseEntity<OnmsgchkOutputVO>(outputVO, responseHeader, HttpStatus.OK);
    }

    public void telgrmValidChk(HttpServletRequest request, OnmsgchkInputVO onmsgchkInputVO) {
        if (StringUtils.isEmpty(request.getHeader("trc_no"))) {
            responseHeader.set("ans_cd", "7777");
        }

        if (StringUtils.isEmpty(onmsgchkInputVO.getDeal_dy())) {

        }

        if (StringUtils.isEmpty(onmsgchkInputVO.getCrd_no()) && StringUtils.isEmpty(onmsgchkInputVO.getResd_no())) {

        }
    }
}
