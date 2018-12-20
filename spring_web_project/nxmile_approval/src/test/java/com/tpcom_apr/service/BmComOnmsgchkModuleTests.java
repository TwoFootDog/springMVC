package com.tpcom_apr.service;

import com.tpcom_apr.domain.BmComOnmsgchkInputVO;
import com.tpcom_apr.domain.BmComOnmsgchkOutputVO;
import com.tpcom_apr.service.service_interface.BmComOnmsgchkModule;
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
public class BmComOnmsgchkModuleTests {

    @Setter(onMethod_ = {@Autowired})
    BmComOnmsgchkModule bmComOnmsgchkModule;

    @Test
    public void testBmComOnmsgchkModule() {
        BmComOnmsgchkInputVO bmComOnmsgchkInputVO = new BmComOnmsgchkInputVO();
        bmComOnmsgchkInputVO.setOrgan_cd("5004");
        bmComOnmsgchkInputVO.setTelgrm_no("K110");
        bmComOnmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        bmComOnmsgchkInputVO.setTelgrm_fg("ON");
        ResponseEntity<BmComOnmsgchkOutputVO> bmComOnmsgchkOutputVO = bmComOnmsgchkModule.syncCall(bmComOnmsgchkInputVO);
        log.info("BmComOnmsgChkOutputVO : " + bmComOnmsgchkOutputVO);
    }

}
