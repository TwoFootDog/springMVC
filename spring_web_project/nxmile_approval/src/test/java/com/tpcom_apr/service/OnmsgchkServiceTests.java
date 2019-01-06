package com.tpcom_apr.service;

import com.ctc.wstx.util.StringUtil;
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
import org.springframework.util.StringUtils;

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
//        OnmsgchkInputVO onmsgchkInputVO = new OnmsgchkInputVO();
//        onmsgchkInputVO.setOrgan_cd("5104");
//        onmsgchkInputVO.setTelgrm_no("K410");
//        onmsgchkInputVO.setSvc_modu_id("ZPTUTXPTC0001");
//        onmsgchkInputVO.setTelgrm_fg("ON");
//
//        ResponseEntity<OnmsgchkOutputVO> OnmsgchkOutputVO = onmsgchkService.syncCall(httpRequest, onmsgchkInputVO);
//        log.info("OnmsgchkOutputVO : " + OnmsgchkOutputVO);

        String str = "    ";
        String str2 = "  a  ";
        String str3 = "";
        String str4 = null;
        log.info("str : [" + str.trim() + "]");
        log.info("str2 : [" + str2 + "]");
        log.info("str2 : [" + str2 + "]");
        log.info("str3 : [" + str3.trim() + "]");

        log.info("str isempty : " + StringUtils.isEmpty(str));
        log.info("str.trim isempty : " + StringUtils.isEmpty(str.trim()));
        log.info("str2 isempty : " + StringUtils.isEmpty(str2));
        log.info("str3 isempty : " + StringUtils.isEmpty(str3));


        if (str.trim() == null) {
            log.info("str은 null입니다");
        }
        if(str3 == null) {
            log.info("str3은 null입니다");
        }
        if(str3.trim() == null) {
            log.info("str3.trim은 null입니다");
        }

        if(!StringUtils.isEmpty(str4) && str4.trim() == null) {
            log.info("str4.trim은 null입니다");
        }
    }

}
