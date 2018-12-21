package com.tpcom_apr.service;

import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import com.tpcom_apr.domain.Rul_svcavl_con_tpcom_vs2001InputVO;
import com.tpcom_apr.domain.Rul_svcavl_con_tpcom_vs2001OutputVO;
import com.tpcom_apr.mapper.Rul_svcavl_conMapper;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
@Log4j
@AllArgsConstructor
public class OnmsgchkServiceImpl implements OnmsgchkService {
    Rul_svcavl_conMapper rul_svcavl_conMapper;

    public ResponseEntity<OnmsgchkOutputVO> syncCall(@RequestBody OnmsgchkInputVO inputVO) {

        Rul_svcavl_con_tpcom_vs2001OutputVO rul_svcavl_con_tpcom_vs2001OutputVO =
                rul_svcavl_conMapper.rul_svcavl_con_tpcom_vs2001(
                new Rul_svcavl_con_tpcom_vs2001InputVO(
                        inputVO.getOrgan_cd(),
                        inputVO.getTelgrm_no(),
                        inputVO.getSvc_modu_id(),
                        inputVO.getTelgrm_fg()));

        log.info(rul_svcavl_con_tpcom_vs2001OutputVO);

        OnmsgchkOutputVO outputVO = new OnmsgchkOutputVO(
                rul_svcavl_con_tpcom_vs2001OutputVO.getMbrsh_pgm_id(),
                rul_svcavl_con_tpcom_vs2001OutputVO.getTelgrm_typ(),
                "0000",
                rul_svcavl_con_tpcom_vs2001OutputVO.getMsg_fg()
        );
        HttpHeaders httpHeaders = new HttpHeaders();
        return new ResponseEntity<OnmsgchkOutputVO>(outputVO, httpHeaders, HttpStatus.OK);
    }
}
