package com.tpcom_apr.service;


import com.tpcom_apr.domain.Apr_dealtr_trn_tpcom_vs2002OutputVO;
import com.tpcom_apr.domain.OritrqryInputVO;
import com.tpcom_apr.domain.OritrqryOutputVO;
import com.tpcom_apr.mapper.Apr_dealtr_trnMapper;
import com.tpcom_apr.service.service_interface.OritrqryService;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class OritrqryServiceTests {

    @Before
    public void setUp() {

    }

    @Test
    public void testOritrqryServiceSyncCallOk() {
        /* Mock Object 선언 */
        MockHttpServletRequest request = new MockHttpServletRequest();
        OritrqryService service = mock(OritrqryServiceImpl.class);

        /* 검증 */
        ResponseEntity<OritrqryOutputVO> output = service.syncCall(request, new OritrqryInputVO());
        verify(service).syncCall(any(), any());

    }

    @Test
    public void testOritrqryServiceInnerMethodCallOk() {
        /* Mock Object 선언 */
        MockHttpServletRequest request = new MockHttpServletRequest();
        OritrqryService service = spy(OritrqryServiceImpl.class);
        OritrqryInputVO inputVO = mock(OritrqryInputVO.class);
        Apr_dealtr_trnMapper mapper = mock(Apr_dealtr_trnMapper.class);
        ((OritrqryServiceImpl) service).setApr_dealtr_trnMapper(mapper);

        /* Stub 선언 */
        when(service.sqlTypeSetting(any())).thenReturn(21);
        when(inputVO.getSvc_modu_id()).thenReturn("ZPTUTXPTC0001");
        when(mapper.apr_dealtr_trn_tpcom_vs2002(any()))
                .thenReturn(new Apr_dealtr_trn_tpcom_vs2002OutputVO(
                        "A","20190128","","","","","","","",""
                        ,"","","","",0D,0D, 0L,0L,0L, 0L
                        ,0L,"",0L,0L,0L,0L,0L,0L,0L,""
                        ,"","","","","","","","","",""
                        ,"","","","",0L,0L,"","","",0L
                        ,"","","","","","","","",0D,0L
                        ,"","","","","","","","","",0L));
        /* 검증 */
        ResponseEntity<OritrqryOutputVO> output = service.syncCall(request, inputVO);
        verify(service).changeOrgnAprvNo(any(), any());
        verify(service).sqlTypeSetting(any());
        verify(mapper).apr_dealtr_trn_tpcom_vs2001(any());
    }

    @Test
    public void testOritrqryServiceDataNotFoundError() {
        /* Mock Object 선언 */
        MockHttpServletRequest request = new MockHttpServletRequest();
        OritrqryService service = spy(OritrqryServiceImpl.class);
        OritrqryInputVO inputVO = mock(OritrqryInputVO.class);
        Apr_dealtr_trnMapper mapper = mock(Apr_dealtr_trnMapper.class);
        ((OritrqryServiceImpl) service).setApr_dealtr_trnMapper(mapper);

        /* Stub 선언 */
        when(mapper.apr_dealtr_trn_tpcom_vs2001(any()))
                .thenReturn(null);
        /* 검증 */
        ResponseEntity<OritrqryOutputVO> output = service.syncCall(request, inputVO);
        verify(service).changeOrgnAprvNo(any(), any());
        verify(service).sqlTypeSetting(any());
        verify(mapper).apr_dealtr_trn_tpcom_vs2001(any());
    }

}
