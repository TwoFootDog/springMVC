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

import javax.servlet.http.HttpServletRequest;

@Service
@Log4j
public class MempntupServiceImpl implements MempntuptService {

    @Setter(onMethod_ = {@Autowired})
    Mbr_mempnt_trnMapper mbr_mempnt_trnMapper;

    private MempntuptOutputVO outputVO;
    private HttpHeaders responseHeader;

    public ResponseEntity<MempntuptOutputVO> syncCall(HttpServletRequest request, MempntuptInputVO inputVO) {

        int result = mbr_mempnt_trnMapper.mbr_mempnt_trn_tpcom_ei2001(
                new Mbr_mempnt_trn_tpcom_ei2001InputVO(
                        inputVO.getCur_pnt(),
                        inputVO.getAvl_pnt(),
                        inputVO.getOrgan_cd(),
                        inputVO.getMbrsh_pgm_id(),
                        inputVO.getMbr_id(),
                        inputVO.getPnt_knd_cd()));
        if (result == 0) {
            throw new ValidException("9080", "포인트 변경 에러");
        } else {
            outputVO = new MempntuptOutputVO(1L);
        }

        return new ResponseEntity<MempntuptOutputVO>(outputVO, responseHeader, HttpStatus.OK);
    }
}
