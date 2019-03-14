package com.tpcom_apr.service;

import com.commons.domain.CustomizeHeaderVO;
import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.GetaprvnoOutputVO;
import com.tpcom_apr.domain.service.wrapper.GetaprvnoInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.GetaprvnoOutputWrapperVO;
import com.tpcom_apr.domain.sql.Aprv_dy_tm_tpcom_vs2001OutputVO;
import com.tpcom_apr.domain.sql.Aprv_no_ocboff_tpcom_vs_2001OutputVO;
import com.tpcom_apr.mapper.Aprv_no_dy_tmMapper;
import com.tpcom_apr.service.serviceInterface.GetaprvnoService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Log4j
public class GetaprvnoServiceImpl implements GetaprvnoService {

    /* 승인일시, 승인번호 조회 SQL */
    @Setter(onMethod_ = {@Autowired})
    private Aprv_no_dy_tmMapper aprv_no_dy_tmMapper;

    /* 승인일시, 승인번호 조회 출력값 */
    private Aprv_dy_tm_tpcom_vs2001OutputVO aprv_dy_tm_tpcom_vs2001OutputVO;
    private Aprv_no_ocboff_tpcom_vs_2001OutputVO aprv_no_ocboff_tpcom_vs_2001OutputVO;

    private GetaprvnoOutputVO outputVO;
    private GetaprvnoOutputWrapperVO outputWrapperVO;

    @Override
    public GetaprvnoOutputWrapperVO syncCall(GetaprvnoInputWrapperVO inputWrapperVO) {

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
            outputVO.setAprv_no(aprv_no_ocboff_tpcom_vs_2001OutputVO.getAprv_no());
            outputVO.setRep_aprv_no(aprv_no_ocboff_tpcom_vs_2001OutputVO.getAprv_no());
        } else {
            throw new ValidException("9080", "승인번호 조회 오류");
        }

        outputWrapperVO = new GetaprvnoOutputWrapperVO();
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
        outputWrapperVO.setBody(outputVO);

        return outputWrapperVO;
    }
}
