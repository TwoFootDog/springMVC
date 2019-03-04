package com.tpcom_apr.service;

import com.commons.domain.CustomizeHeaderVO;
import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.CntrinsertInputVO;
import com.tpcom_apr.domain.service.CntrinsertOutputVO;
import com.tpcom_apr.domain.service.wrapper.CntrinsertInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.CntrinsertOutputWrapperVO;
import com.tpcom_apr.domain.sql.Apr_dealtr_trn_tpcom_ei2001InputVO;
import com.tpcom_apr.domain.sql.Apr_dealtr_trn_tpcom_eu2001InputVO;
import com.tpcom_apr.mapper.Apr_dealtr_trnMapper;
import com.tpcom_apr.service.service_interface.CntrinsertService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.text.SimpleDateFormat;
import java.util.Date;

@Service
@Log4j
@Transactional
public class CntrinsertServiceImpl implements CntrinsertService {

    @Setter(onMethod_ = {@Autowired})
    private Apr_dealtr_trnMapper apr_dealtr_trnMapper;

    private CustomizeHeaderVO header;   // 요청 header
    private CntrinsertInputVO inputVO;  // 요청 body
    private CntrinsertOutputVO outputVO;    // 응답 body
    private CntrinsertOutputWrapperVO outputWrapperVO;  // 응답 header + body


    public CntrinsertOutputWrapperVO syncCall(CntrinsertInputWrapperVO inputWrapperVO) {

        header = inputWrapperVO.getHeader();
        inputVO = inputWrapperVO.getBody();

        try {
            int result = apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_ei2001(
                    new Apr_dealtr_trn_tpcom_ei2001InputVO(inputVO));
            if (result <= 0) {
                throw new ValidException("9080", "취소 거래내역 생성 에러");
            }
        } catch (Exception e) {
//            log.info("error message----------------------------");
//            e.getStackTrace();
            throw new ValidException("9080", "취소 거래내역 생성 에러. 상세 : " + e.getCause());
        }

        String cnclTyp;
        if (!StringUtils.isEmpty(inputVO.getAns_cd()) && inputVO.getAns_cd().equals("60")) {
            cnclTyp = "2";
        } else {
            cnclTyp = "1";
        }
        try {
            int result = apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_eu2001(
                    new Apr_dealtr_trn_tpcom_eu2001InputVO(
                            cnclTyp,
                            inputVO.getAns_cd(),
                            inputVO.getAprv_dy(),
                            inputVO.getAprv_no(),
                            inputVO.getDeal_dy(),
                            header.getOrgan_cd(),
                            inputVO.getMbrsh_pgm_id(),
                            inputVO.getOrgn_aprv_dy(),
                            inputVO.getOrgn_aprv_no(),
                            inputVO.getCrd_no()));
            if (result > 0) {

                outputWrapperVO = new CntrinsertOutputWrapperVO();
                outputWrapperVO.setHeader(
                        new CustomizeHeaderVO(
                                inputWrapperVO.getHeader().getTelgrm_no().substring(0, 3).concat("1"),
                                inputWrapperVO.getHeader().getOrgan_cd(),
                                new SimpleDateFormat("yyyyMMdd").format(new Date()),
                                new SimpleDateFormat("HHmmss").format(new Date()),
                                inputWrapperVO.getHeader().getTrc_no(),
                                inputWrapperVO.getHeader().getTelgrm_fg(),
                                "0000",
                                ""));

                outputVO = new CntrinsertOutputVO(result);
                outputWrapperVO.setBody(outputVO);
            } else {
                throw new ValidException("9080", "원거래내역 갱신 에러");
            }
        } catch (Exception e) {
            throw new ValidException("9080", "원거래내역 갱신 에러. 상세 : " + e.getCause());
        }


        log.info("거래내역은 금방 갱신되지유?????" + new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss").format(System.currentTimeMillis()));

        return outputWrapperVO;
    }
        /*Map<String, String> header = requestHeaders.toSingleValueMap();

        try {
            int result = apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_ei2001(
                    new Apr_dealtr_trn_tpcom_ei2001InputVO(inputVO));
            if (result <= 0) {
                throw new ValidException(requestHeaders, "9080", "취소 거래내역 생성 에러");
            }
        } catch (Exception e) {
            throw new ValidException(requestHeaders, "9080", "취소 거래내역 생성 에러. 상세 : " + e.getCause());
        }

        String cnclTyp;
        if (!StringUtils.isEmpty(inputVO.getAns_cd()) && inputVO.getAns_cd().equals("60")) {
            cnclTyp = "2";
        } else {
            cnclTyp = "1";
        }
        try {
            int result = apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_eu2001(
                    new Apr_dealtr_trn_tpcom_eu2001InputVO(
                            cnclTyp,
                            inputVO.getAns_cd(),
                            inputVO.getAprv_dy(),
                            inputVO.getAprv_no(),
                            inputVO.getDeal_dy(),
                            header.get("organ_cd"),
                            inputVO.getMbrsh_pgm_id(),
                            inputVO.getOrgn_aprv_dy(),
                            inputVO.getOrgn_aprv_no(),
                            inputVO.getCrd_no()));
            if (result > 0 ) {
                outputVO = new CntrinsertOutputVO(result);
            } else {
                throw new ValidException(requestHeaders, "9080", "원거래내역 갱신 에러");
            }
        } catch (Exception e) {
            throw new ValidException(requestHeaders, "9080", "원거래내역 갱신 에러. 상세 : " + e.getCause());
        }


        log.info("거래내역은 금방 갱신되지유?????" + new SimpleDateFormat("yyyy/MM/dd/ HH:mm:ss").format(System.currentTimeMillis()));
        responseHeaders = new HttpHeaders();
        return new ResponseEntity<CntrinsertOutputVO>(outputVO, responseHeaders, HttpStatus.OK);
    }*/
}
