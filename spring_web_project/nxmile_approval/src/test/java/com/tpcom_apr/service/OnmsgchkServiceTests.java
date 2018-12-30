package com.tpcom_apr.service;

import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class OnmsgchkServiceTests {

    @Setter(onMethod_ = {@Autowired})
    OnmsgchkService onmsgchkService;

    HttpServletRequest httpRequest;

    @Test
    public void testOnmsgchkService() {
        OnmsgchkInputVO onmsgchkInputVO = new OnmsgchkInputVO();
        onmsgchkInputVO.setOrgan_cd("5004");
        onmsgchkInputVO.setTelgrm_no("K410");
        onmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        onmsgchkInputVO.setTelgrm_fg("ON");
        ResponseEntity<OnmsgchkOutputVO> OnmsgchkOutputVO = onmsgchkService.syncCall(httpRequest, onmsgchkInputVO);
        log.info("OnmsgchkOutputVO : " + OnmsgchkOutputVO);
    }

}
