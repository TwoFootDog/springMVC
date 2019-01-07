package com.tpcom_apr.service;


import com.tpcom_apr.domain.OritrqryInputVO;
import com.tpcom_apr.domain.OritrqryOutputVO;
import com.tpcom_apr.service.service_interface.OritrqryService;
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
public class OritrqryServiceTests {

    @Setter(onMethod_ = {@Autowired})
    OritrqryService oritrqryService;


    HttpServletRequest request;

    @Test
    public void oritrqryTest() {

        OritrqryInputVO oritrqryInputVO =
                new OritrqryInputVO("ZPTUTXPTC0001",
                        "9999999999",
                        "A",
                        "89004518",
                        "2200111133331352",
                        "20190103",
                        "F88405584",
                        "",
                        10000L,
                        "",
                        "");
        request.setAttribute("organ_cd", "5004");
        ResponseEntity<OritrqryOutputVO> oritrqryOutputVO = oritrqryService.syncCall(request, oritrqryInputVO);
    log.info("oritrqryOutputVO : " + oritrqryOutputVO);
    }
}
