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
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class OnmsgchkServiceTests {

    @Setter(onMethod_ = {@Autowired})
    OnmsgchkService onmsgchkService;

    private MockHttpServletRequest request;

    @Test
    public void testOnmsgchkService() {
        OnmsgchkInputVO onmsgchkInputVO = new OnmsgchkInputVO();
        onmsgchkInputVO.setOrgan_cd("5104");
        onmsgchkInputVO.setTelgrm_no("K410");
        onmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
        onmsgchkInputVO.setTelgrm_fg("ON");
        onmsgchkInputVO.setMcht_no("99999999");
        onmsgchkInputVO.setDeal_dy("20190110");
        onmsgchkInputVO.setCrd_no("2222222222222222");

        request = new MockHttpServletRequest();
        request.addHeader("trc_no", "11111111");

        ResponseEntity<OnmsgchkOutputVO> onmsgchkOutputVO =
            onmsgchkOutputVO = onmsgchkService.syncCall(request, onmsgchkInputVO);


        log.info("OnmsgchkOutputVO : " + onmsgchkOutputVO);

    }

}
