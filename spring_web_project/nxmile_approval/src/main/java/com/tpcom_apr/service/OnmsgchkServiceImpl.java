package com.tpcom_apr.service;

import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import com.tpcom_apr.domain.Rul_svcavl_con_tpcom_vs2001InputVO;
import com.tpcom_apr.domain.Rul_svcavl_con_tpcom_vs2001OutputVO;
import com.tpcom_apr.mapper.Rul_svcavl_conMapper;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import javax.servlet.http.HttpServletRequest;

@Service
@Log4j
//@AllArgsConstructor
public class OnmsgchkServiceImpl implements OnmsgchkService {
    @Setter(onMethod_ = {@Autowired})
    Rul_svcavl_conMapper rul_svcavl_conMapper;

    HttpServletRequest header;
    OnmsgchkInputVO inputVO;
    OnmsgchkOutputVO outputVO;
    Rul_svcavl_con_tpcom_vs2001OutputVO rul_svcavl_con_tpcom_vs2001OutputVO;

    public ResponseEntity<OnmsgchkOutputVO> syncCall(HttpServletRequest requestHeader, OnmsgchkInputVO onmsgchkInputVO) {
        this.header = requestHeader;
        this.inputVO = onmsgchkInputVO;
        HttpHeaders responseHeader = new HttpHeaders();

             rul_svcavl_con_tpcom_vs2001OutputVO =
                    rul_svcavl_conMapper.rul_svcavl_con_tpcom_vs2001(
                            new Rul_svcavl_con_tpcom_vs2001InputVO(
                                    inputVO.getOrgan_cd(),
                                    inputVO.getTelgrm_no(),
                                    inputVO.getSvc_modu_id(),
                                    inputVO.getTelgrm_fg()));

        log.info("rul_svcavl_con_tpcom_vs2001OutputVO : " + rul_svcavl_con_tpcom_vs2001OutputVO);
        if (rul_svcavl_con_tpcom_vs2001OutputVO == null) {
            responseHeader.set("ans_cd", "7777");
            log.info("null!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
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

    public void telgrmValidChk() {
        if (header.getHeader("trc_no") == null ) {

        }

        if (inputVO.getDeal_dy() == null ) {

        }

        if (inputVO.getCrd_no() == null  && inputVO.getResd_no() == null) {

        }
    }
}
