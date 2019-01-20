package com.tpcom_apr.service;

import com.tpcom_apr.domain.Crd_master_mst_tpcom_vs2005InputVO;
import com.tpcom_apr.domain.Crd_master_mst_tpcom_vs2005OutputVO;
import com.tpcom_apr.domain.MeminfqryInputVO;
import com.tpcom_apr.domain.MeminfqryOutputVO;
import com.tpcom_apr.mapper.Crd_master_mstMapper;
import com.tpcom_apr.service.service_interface.MeminfqryService;
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
public class MeminfqryServiceImpl implements MeminfqryService {

    @Setter(onMethod_ = {@Autowired})
    private Crd_master_mstMapper crd_master_mstMapper;
    private Crd_master_mst_tpcom_vs2005OutputVO crd_master_mst_tpcom_vs2005OutputVO;

    private HttpHeaders responseHeader;
    private MeminfqryOutputVO outputVO;

    public ResponseEntity<MeminfqryOutputVO> syncCall(HttpServletRequest request, MeminfqryInputVO inputVO) {

        crd_master_mst_tpcom_vs2005OutputVO =
                crd_master_mstMapper.crd_master_mst_tpcom_vs_2005(
                        new Crd_master_mst_tpcom_vs2005InputVO(
                                inputVO.getMbrsh_pgm_id(),
                                inputVO.getCrd_no()));
        outputVO = new MeminfqryOutputVO(crd_master_mst_tpcom_vs2005OutputVO);

        return new ResponseEntity<MeminfqryOutputVO>(outputVO, responseHeader, HttpStatus.OK);
    }
}
