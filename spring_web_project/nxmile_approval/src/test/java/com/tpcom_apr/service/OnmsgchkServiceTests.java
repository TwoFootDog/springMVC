package com.tpcom_apr.service;

import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class OnmsgchkServiceTests {

    @Setter(onMethod_ = {@Autowired})
    com.tpcom_apr.service.service_interface.OnmsgchkService OnmsgchkService;

    @Test
    public void testOnmsgchkService() {
        OnmsgchkInputVO OnmsgchkInputVO = new OnmsgchkInputVO();
        OnmsgchkInputVO.setOrgan_cd("5004");
        OnmsgchkInputVO.setTelgrm_no("K410");
        OnmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        OnmsgchkInputVO.setTelgrm_fg("ON");
        ResponseEntity<OnmsgchkOutputVO> OnmsgchkOutputVO = OnmsgchkService.syncCall(OnmsgchkInputVO);
        log.info("OnmsgchkOutputVO : " + OnmsgchkOutputVO);
    }

}
