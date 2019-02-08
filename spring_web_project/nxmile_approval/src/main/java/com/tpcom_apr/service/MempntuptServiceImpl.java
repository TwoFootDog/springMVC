package com.tpcom_apr.service;

import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.MempntuptInputVO;
import com.tpcom_apr.domain.service.MempntuptOutputVO;
import com.tpcom_apr.domain.sql.Mbr_mempnt_trn_tpcom_ei2001InputVO;
import com.tpcom_apr.mapper.Mbr_mempnt_trnMapper;
import com.tpcom_apr.service.service_interface.MempntuptService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Log4j
@Transactional
public class MempntuptServiceImpl implements MempntuptService {

    @Setter(onMethod_ = {@Autowired})
    Mbr_mempnt_trnMapper mbr_mempnt_trnMapper;

    private MempntuptOutputVO outputVO;
    private HttpHeaders responseHeaders;

    public ResponseEntity<MempntuptOutputVO> syncCall(HttpHeaders requestHeaders, MempntuptInputVO inputVO) {

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
                outputVO = new MempntuptOutputVO(result);
            } else {
                throw new ValidException(requestHeaders, "9080", "포인트 변경 에러");
            }
        } catch (Exception e) {
            throw new ValidException(requestHeaders, "9080", "포인트 변경 에러. 상세 : " + e.getCause());
        }

        responseHeaders = new HttpHeaders();
        return new ResponseEntity<MempntuptOutputVO>(outputVO, responseHeaders, HttpStatus.OK);
    }
}
