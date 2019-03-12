package com.tpcom_apr.service;

import com.commons.domain.CustomizeHeaderVO;
import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.MempntuptInputVO;
import com.tpcom_apr.domain.service.MempntuptOutputVO;
import com.tpcom_apr.domain.service.wrapper.MempntuptInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.MempntuptOutputWrapperVO;
import com.tpcom_apr.domain.sql.Mbr_mempnt_trn_tpcom_ei2001InputVO;
import com.tpcom_apr.mapper.Mbr_mempnt_trnMapper;
import com.tpcom_apr.service.serviceInterface.MempntuptService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;

import static java.lang.Thread.sleep;


@Service
@Log4j
@Transactional
@Async
public class MempntuptServiceImpl implements MempntuptService {

    @Setter(onMethod_ = {@Autowired})
    Mbr_mempnt_trnMapper mbr_mempnt_trnMapper;

    private CustomizeHeaderVO header;   // 요청 header
    private MempntuptInputVO inputVO;   // 요청 body
    private MempntuptOutputVO outputVO; // 응답 body
    private MempntuptOutputWrapperVO outputWrapperVO;   // 응답 header + body



    public MempntuptOutputWrapperVO syncCall(MempntuptInputWrapperVO inputWrapperVO) {

        header = inputWrapperVO.getHeader();
        inputVO = inputWrapperVO.getBody();
        try {
            sleep(4000);
            log.info("포인트 업데이트는 좀 늦게 되지유????" + new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss").format(System.currentTimeMillis()));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        try {
            int result = mbr_mempnt_trnMapper.mbr_mempnt_trn_tpcom_ei2001(
                    new Mbr_mempnt_trn_tpcom_ei2001InputVO(
                            inputVO.getCur_pnt(),
                            inputVO.getAvl_pnt(),
                            inputVO.getOrgan_cd(),
                            inputVO.getMbrsh_pgm_id(),
                            inputVO.getMbr_id(),
                            inputVO.getPnt_knd_cd()));
            if (result > 0) {
                outputWrapperVO = new MempntuptOutputWrapperVO();
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
                outputVO = new MempntuptOutputVO(result);
                outputWrapperVO.setBody(outputVO);
            } else {
                throw new ValidException("9080", "포인트 변경 에러");
            }
        } catch (Exception e) {
            throw new ValidException("9080", "포인트 변경 에러. 상세 : " + e.getCause());
        }

        return outputWrapperVO;
    }
}
