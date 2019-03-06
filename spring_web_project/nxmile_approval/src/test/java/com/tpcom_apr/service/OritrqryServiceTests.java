package com.tpcom_apr.service;


import com.commons.domain.CustomizeHeaderVO;
import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.wrapper.OritrqryInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.OritrqryOutputWrapperVO;
import com.tpcom_apr.domain.sql.Apr_dealtr_trn_tpcom_vs2002OutputVO;
import com.tpcom_apr.domain.service.OritrqryInputVO;
import com.tpcom_apr.domain.service.OritrqryOutputVO;
import com.tpcom_apr.mapper.Apr_dealtr_trnMapper;
import com.tpcom_apr.service.service_interface.OritrqryService;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
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
        HttpHeaders headers = mock(HttpHeaders.class);
        OritrqryService service = mock(OritrqryServiceImpl.class);

        /* 검증 */
        OritrqryOutputWrapperVO output = service.syncCall(new OritrqryInputWrapperVO());
        verify(service).syncCall(any());
    }

    @Test
    public void testOritrqryServiceInnerMethodCallOk() {
        /* Mock Object 선언 */
        CustomizeHeaderVO header = mock(CustomizeHeaderVO.class);
        OritrqryService service = spy(OritrqryServiceImpl.class);
        OritrqryInputWrapperVO inputWrapperVO = mock(OritrqryInputWrapperVO.class);
        OritrqryInputVO input = mock(OritrqryInputVO.class);
        Apr_dealtr_trnMapper mapper = mock(Apr_dealtr_trnMapper.class);
        ((OritrqryServiceImpl) service).setApr_dealtr_trnMapper(mapper);

        /* Stub 선언 */
        when(inputWrapperVO.getHeader()).thenReturn(header);
        when(inputWrapperVO.getBody()).thenReturn(input);
        when(inputWrapperVO.getHeader().getTelgrm_no()).thenReturn("K410");
        when(input.getSvc_modu_id()).thenReturn("ZPTUTXPTC0001");
        when(input.getAns_cd()).thenReturn("");
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
        OritrqryOutputWrapperVO output = service.syncCall(inputWrapperVO);
        verify(service).changeOrgnAprvNo(any(), any());
        verify(service).sqlTypeSetting(any());
        verify(mapper).apr_dealtr_trn_tpcom_vs2002(any());
    }

    @Test
    public void testOritrqryServiceDataNotFoundError() {
        /* Mock Object 선언 */
        CustomizeHeaderVO header = mock(CustomizeHeaderVO.class);
        OritrqryService service = spy(OritrqryServiceImpl.class);
        OritrqryInputWrapperVO inputWrapperVO = mock(OritrqryInputWrapperVO.class);
        OritrqryInputVO input = mock(OritrqryInputVO.class);
        Apr_dealtr_trnMapper mapper = mock(Apr_dealtr_trnMapper.class);
        ((OritrqryServiceImpl) service).setApr_dealtr_trnMapper(mapper);

        /* Stub 선언 */
        when(inputWrapperVO.getHeader()).thenReturn(header);
        when(inputWrapperVO.getBody()).thenReturn(input);
        when(input.getSvc_modu_id()).thenReturn("ZPTUTXPTC0001");
        when(input.getAns_cd()).thenReturn("");
        when(mapper.apr_dealtr_trn_tpcom_vs2002(any()))
                .thenReturn(null);
        /* 검증 */
        try {
            OritrqryOutputWrapperVO output = service.syncCall(inputWrapperVO);
        } catch (ValidException e) {
            if (e.getAns_cd().equals("7777")) {
               log.info("에러코드 : " + e.getAns_cd() + ". 에러 검증 완료");
            } else {
                throw new ValidException(e.getAns_cd(), e.getAns_msg());
            }
        }

        verify(service).changeOrgnAprvNo(any(), any());
        verify(service).sqlTypeSetting(any());
        verify(mapper).apr_dealtr_trn_tpcom_vs2002(any());
    }

}
