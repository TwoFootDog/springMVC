package com.tpcom_apr.service;

import com.commons.exception.ValidException;
import com.ctc.wstx.util.StringUtil;
import com.tpcom_apr.domain.service.GetaprvnoInputVO;
import com.tpcom_apr.domain.service.GetaprvnoOutputVO;
import com.tpcom_apr.domain.sql.Aprv_dy_tm_tpcom_vs2001OutputVO;
import com.tpcom_apr.domain.sql.Aprv_no_ocboff_tpcom_vs_2001OutputVO;
import com.tpcom_apr.mapper.Aprv_no_dy_tmMapper;
import com.tpcom_apr.service.service_interface.GetaprvnoService;
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
public class GetaprvnoServiceImpl implements GetaprvnoService {

    /* 승인일시, 승인번호 조회 SQL */
    @Setter(onMethod_ = {@Autowired})
    Aprv_no_dy_tmMapper aprv_no_dy_tmMapper;

    /* 승인일시, 승인번호 조회 출력값 */
    Aprv_dy_tm_tpcom_vs2001OutputVO aprv_dy_tm_tpcom_vs2001OutputVO;
    Aprv_no_ocboff_tpcom_vs_2001OutputVO aprv_no_ocboff_tpcom_vs_2001OutputVO;

    /* 결과값 */
    HttpHeaders responseHeader;
    GetaprvnoOutputVO outputVO;

    public ResponseEntity<GetaprvnoOutputVO> syncCall(HttpServletRequest request, GetaprvnoInputVO inputVO) {

        responseHeader = new HttpHeaders();
        outputVO = new GetaprvnoOutputVO();

        /* 승인일자, 승인시간 조회 */
        aprv_dy_tm_tpcom_vs2001OutputVO = aprv_no_dy_tmMapper.aprv_dy_tm_tpcom_vs2001();
        if (!StringUtils.isEmpty(aprv_dy_tm_tpcom_vs2001OutputVO)) {
            outputVO.setAprv_dy(aprv_dy_tm_tpcom_vs2001OutputVO.getAprv_dy());
            outputVO.setAprv_tm(aprv_dy_tm_tpcom_vs2001OutputVO.getAprv_tm());
        } else {
            throw new ValidException("9080", "승인일시 조회 오류");
        }

        /* 승인번호 조회*/
        aprv_no_ocboff_tpcom_vs_2001OutputVO = aprv_no_dy_tmMapper.aprv_no_ocboff_tpcom_vs2001();
        if (!StringUtils.isEmpty(aprv_no_ocboff_tpcom_vs_2001OutputVO)) {
            responseHeader.add("ans_cd1", "00");
            responseHeader.add("ans_cd2", "00");
            outputVO.setAprv_no(aprv_no_ocboff_tpcom_vs_2001OutputVO.getAprv_no());
        } else {
            throw new ValidException("9080", "승인번호 조회 오류");
        }

        return new ResponseEntity<GetaprvnoOutputVO>(outputVO, responseHeader, HttpStatus.OK);
    }
}
