package com.tpcom_apr.service;


import com.tpcom_apr.domain.Crd_master_mst_tpcom_vs2005InputVO;
import com.tpcom_apr.domain.Crd_master_mst_tpcom_vs2005OutputVO;
import com.tpcom_apr.domain.MeminfqryInputVO;
import com.tpcom_apr.domain.MeminfqryOutputVO;
import com.tpcom_apr.mapper.Crd_master_mstMapper;
import com.tpcom_apr.service.service_interface.MeminfqryService;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class MeminfqryServiceTests {

    @Test
    public void testMeminfqryServiceSynccallOk() {
        /* Mock Object 선언 */
        MockHttpServletRequest request = new MockHttpServletRequest();
        MeminfqryService service = mock(MeminfqryServiceImpl.class);

        /* Stub 선언 */
        ResponseEntity<MeminfqryOutputVO> output = service.syncCall(request, new MeminfqryInputVO());

        /* 검증 */
        verify(service).syncCall(any(), any());
    }

    @Test
    public void testMeminfqryServiceInnterMethodCallOk() {
        /* Mock Object 선언 */
        MockHttpServletRequest request = new MockHttpServletRequest();
        MeminfqryService service = spy(MeminfqryServiceImpl.class);
        MeminfqryInputVO inputVO = mock(MeminfqryInputVO.class);
        Crd_master_mstMapper mapper = mock(Crd_master_mstMapper.class);
        ((MeminfqryServiceImpl) service).setCrd_master_mstMapper(mapper);

        /* Stub 선언 */
        when(mapper.crd_master_mst_tpcom_vs_2005(any()))
                .thenReturn(new Crd_master_mst_tpcom_vs2005OutputVO(
                        "111111111","O11111","","","","","","","","",
                        "","","","","","","","","","",
                        "","","","","","","","",""));

        /* 검증 */
        ResponseEntity<MeminfqryOutputVO> outputVO = service.syncCall(request, inputVO);
        verify(service).syncCall(any(), any());
        verify(mapper).crd_master_mst_tpcom_vs_2005(any());
    }
}
