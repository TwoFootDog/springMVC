package com.tpcom_apr.service;

import com.commons.domain.CustomizeHeaderVO;
import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.wrapper.MeminfqryInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.MeminfqryOutputWrapperVO;
import com.tpcom_apr.domain.sql.Crd_master_mst_tpcom_vs2005InputVO;
import com.tpcom_apr.domain.sql.Crd_master_mst_tpcom_vs2005OutputVO;
import com.tpcom_apr.domain.service.MeminfqryInputVO;
import com.tpcom_apr.domain.service.MeminfqryOutputVO;
import com.tpcom_apr.mapper.Crd_master_mstMapper;
import com.tpcom_apr.service.serviceInterface.MeminfqryService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Log4j
public class MeminfqryServiceImpl implements MeminfqryService {

    @Setter(onMethod_ = {@Autowired})
    private Crd_master_mstMapper crd_master_mstMapper;
    private Crd_master_mst_tpcom_vs2005OutputVO crd_master_mst_tpcom_vs2005OutputVO;

    private CustomizeHeaderVO header;   // 요청 header
    private MeminfqryInputVO inputVO;   // 요청 body
    private MeminfqryOutputVO outputVO; // 응답 body
    private MeminfqryOutputWrapperVO outputWrapperVO;   // 응답 header + body


    public MeminfqryOutputWrapperVO syncCall(MeminfqryInputWrapperVO inputWrapperVO) {

        inputVO = inputWrapperVO.getBody();
        crd_master_mst_tpcom_vs2005OutputVO =
                crd_master_mstMapper.crd_master_mst_tpcom_vs2005(
                        new Crd_master_mst_tpcom_vs2005InputVO(
                                inputVO.getMbrsh_pgm_id(),
                                inputVO.getCrd_no()));
        if (!StringUtils.isEmpty(crd_master_mst_tpcom_vs2005OutputVO)) {
            outputWrapperVO = new MeminfqryOutputWrapperVO();
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
            outputVO = new MeminfqryOutputVO(crd_master_mst_tpcom_vs2005OutputVO);
            outputWrapperVO.setBody(outputVO);
        } else if(StringUtils.isEmpty(crd_master_mst_tpcom_vs2005OutputVO)) {
            throw new ValidException("8811", "데이터 미존재");
        } else {
            throw new ValidException("9080", "시스템실 연락바람");
        }

        return outputWrapperVO;
    }
}
