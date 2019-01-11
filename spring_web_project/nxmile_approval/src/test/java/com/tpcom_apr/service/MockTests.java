package com.tpcom_apr.service;


import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
public class MockTests {

    @Mock
    OnmsgchkService onmsgchkService;

    @Mock
    OnmsgchkInputVO onmsgchkInputVO;

    @Test
    public void mockTest() {
        MockitoAnnotations.initMocks(this);
        assertTrue(onmsgchkService!=null);
    }

    @Test
    public void whenTest() {
        when(onmsgchkInputVO.getAns_cd1()).thenReturn("0000");
        when(onmsgchkInputVO.getOrgn_deal_amt()).thenReturn(10000L);
        assertTrue("0000".equals(onmsgchkInputVO.getAns_cd1()));
        assertTrue(10000L != onmsgchkInputVO.getOrgn_deal_amt());

    }

}
