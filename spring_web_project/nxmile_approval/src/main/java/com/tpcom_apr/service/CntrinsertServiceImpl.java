package com.tpcom_apr.service;

import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.CntrinsertInputVO;
import com.tpcom_apr.domain.service.CntrinsertOutputVO;
import com.tpcom_apr.domain.sql.Apr_dealtr_trn_tpcom_ei2001InputVO;
import com.tpcom_apr.mapper.Apr_dealtr_trnMapper;
import com.tpcom_apr.service.service_interface.CntrinsertService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@Log4j
public class CntrinsertServiceImpl implements CntrinsertService {

    @Setter(onMethod_ = {@Autowired})
    private Apr_dealtr_trnMapper apr_dealtr_trnMapper;

    HttpHeaders responseHeaders;
    CntrinsertOutputVO outputVO;


    public ResponseEntity<CntrinsertOutputVO> syncCall(HttpHeaders requestHeaders, CntrinsertInputVO inputVO) {

        int result = apr_dealtr_trnMapper.apr_dealtr_trn_tpcom_ei2001(
                        new Apr_dealtr_trn_tpcom_ei2001InputVO(inputVO));

        if (result > 0) {
            outputVO = new CntrinsertOutputVO(result);
        } else {
            throw new ValidException(requestHeaders, "9080", "취소 거래내역 생성 에러");
        }

        responseHeaders = new HttpHeaders();
        return new ResponseEntity<CntrinsertOutputVO>(outputVO, responseHeaders, HttpStatus.OK);
    }
}
