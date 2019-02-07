package com.tpcom_apr.service;


import com.commons.exception.ValidException;
import com.tpcom_apr.domain.sql.Crd_master_mst_tpcom_vs2005OutputVO;
import com.tpcom_apr.domain.service.MeminfqryInputVO;
import com.tpcom_apr.domain.service.MeminfqryOutputVO;
import com.tpcom_apr.mapper.Crd_master_mstMapper;
import com.tpcom_apr.service.service_interface.MeminfqryService;
import lombok.extern.log4j.Log4j;
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
@Log4j
public class MeminfqryServiceTests {

    @Test
    public void testMeminfqryServiceSynccallOk() {
        /* Mock Object 선언 */
        HttpHeaders headers = mock(HttpHeaders.class);
        MeminfqryService service = mock(MeminfqryServiceImpl.class);

        /* Stub 선언 */
        ResponseEntity<MeminfqryOutputVO> output = service.syncCall(headers, new MeminfqryInputVO());

        /* 검증 */
        verify(service).syncCall(any(), any());
    }

    @Test
    public void testMeminfqryServiceMapperCallOk() {
        /* Mock Object 선언 */
        HttpHeaders headers = mock(HttpHeaders.class);
        MeminfqryService service = spy(MeminfqryServiceImpl.class);
        MeminfqryInputVO input = mock(MeminfqryInputVO.class);
        Crd_master_mstMapper mapper = mock(Crd_master_mstMapper.class);
        ((MeminfqryServiceImpl) service).setCrd_master_mstMapper(mapper);

        /* Stub 선언 */
        when(mapper.crd_master_mst_tpcom_vs2005(any()))
                .thenReturn(new Crd_master_mst_tpcom_vs2005OutputVO(
                        "111111111","O11111","","","","","","","","",
                        "","","",""));

        /* 검증 */
        ResponseEntity<MeminfqryOutputVO> output = service.syncCall(headers, input);
        verify(service).syncCall(any(), any());
        verify(mapper).crd_master_mst_tpcom_vs2005(any());
    }

    @Test
    public void testMeminfqryServiceDataNotFound() {
        /* Mock Object 선언 */
        HttpHeaders headers = mock(HttpHeaders.class);
        MeminfqryService service = spy(MeminfqryServiceImpl.class);
        MeminfqryInputVO input = mock(MeminfqryInputVO.class);
        Crd_master_mstMapper mapper = mock(Crd_master_mstMapper.class);
        ((MeminfqryServiceImpl) service).setCrd_master_mstMapper(mapper);

        /* Stub 선언*/
        when(input.getMbrsh_pgm_id()).thenReturn("A");
        when(input.getCrd_no()).thenReturn("1111111111111111");
        when(mapper.crd_master_mst_tpcom_vs2005(any())).thenReturn(null);

        /* 검증 */
        try {
            ResponseEntity<MeminfqryOutputVO> output = service.syncCall(headers, input);
        } catch (ValidException e) {
            if (e.getAns_cd().equals("8811")) {
                log.info("에러코드 : " + e.getAns_cd() + ". 에러 검증 완료");
            }else {
                throw new ValidException(headers, e.getAns_cd(), e.getAns_msg());
            }
        }
    }
}
