package com.tpcom_apr.service;


import com.commons.domain.CustomizeHeaderVO;
import com.tpcom_apr.domain.service.GetaprvnoInputVO;
import com.tpcom_apr.domain.service.GetaprvnoOutputVO;
import com.tpcom_apr.domain.service.wrapper.GetaprvnoInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.GetaprvnoOutputWrapperVO;
import com.tpcom_apr.domain.sql.Aprv_dy_tm_tpcom_vs2001OutputVO;
import com.tpcom_apr.domain.sql.Aprv_no_ocboff_tpcom_vs_2001OutputVO;
import com.tpcom_apr.mapper.Aprv_no_dy_tmMapper;
import com.tpcom_apr.service.service_interface.GetaprvnoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
public class GetaprvnoServiceTests {

    @Test
    public void testGetaprvnoServiceSyncCallOk() {
        /* Mock Object 선언 */
        GetaprvnoService service = mock(GetaprvnoServiceImpl.class);

        /* 서비스 호출 */
        GetaprvnoOutputWrapperVO output = service.syncCall(new GetaprvnoInputWrapperVO());

        /* 검증 */
        verify(service).syncCall(any());
    }

    @Test
    public void testGetaprvnoServiceMapperCallOk() {
        /* Mock Object 선언 */
        GetaprvnoService service = spy(GetaprvnoServiceImpl.class);
        GetaprvnoInputWrapperVO inputWrapperVO = mock(GetaprvnoInputWrapperVO.class);
        Aprv_no_dy_tmMapper mapper = mock(Aprv_no_dy_tmMapper.class);
        ((GetaprvnoServiceImpl) service).setAprv_no_dy_tmMapper(mapper);

        /* stub 설정*/
        when(inputWrapperVO.getHeader()).thenReturn(new CustomizeHeaderVO("K411","5004","20190301","000000","1111111111","ON","00","00",""));
        when(mapper.aprv_dy_tm_tpcom_vs2001()).thenReturn(new Aprv_dy_tm_tpcom_vs2001OutputVO("20190130","170000"));
        when(mapper.aprv_no_ocboff_tpcom_vs2001()).thenReturn(new Aprv_no_ocboff_tpcom_vs_2001OutputVO("F88888888"));

        /* 서비스 호출 */
        GetaprvnoOutputWrapperVO output = service.syncCall(inputWrapperVO);

        /* 검증 */
        verify(service).syncCall(any());
        verify(mapper).aprv_no_ocboff_tpcom_vs2001();
        verify(mapper).aprv_dy_tm_tpcom_vs2001();
    }
}
