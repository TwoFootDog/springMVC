package com.tpcom_apr.service;

import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.MempntuptInputVO;
import com.tpcom_apr.domain.service.MempntuptOutputVO;
import com.tpcom_apr.domain.sql.Mbr_mempnt_trn_tpcom_ei2001InputVO;
import com.tpcom_apr.mapper.Mbr_mempnt_trnMapper;
import com.tpcom_apr.service.service_interface.MempntuptService;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
public class MempntuptServiceTests {

    @Test
    public void testMempntuptServiceSyncCallOk() {
        /* Mock object 선언 */
        HttpHeaders headers = mock(HttpHeaders.class);
        MempntuptService service = mock(MempntuptServiceImpl.class);

        /* 서비스 호출*/
        ResponseEntity<MempntuptOutputVO> output = service.syncCall(headers, new MempntuptInputVO());

        /* 정상 호출여부 검증 */
        verify(service).syncCall(any(), any());
    }

    @Test
    public void testMempntuptServiceMapperCallOk() {
        /* Mock object 선언 */
        HttpHeaders headers = mock(HttpHeaders.class);
        MempntuptService service = spy(MempntuptServiceImpl.class);
        MempntuptInputVO inputVO = mock(MempntuptInputVO.class);
        Mbr_mempnt_trnMapper mapper = mock(Mbr_mempnt_trnMapper.class);
        ((MempntuptServiceImpl) service).setMbr_mempnt_trnMapper(mapper);

        /* stub 선언*/
        when(inputVO.getCur_pnt()).thenReturn(0L);
        when(inputVO.getAvl_pnt()).thenReturn(0L);
        when(inputVO.getOrgan_cd()).thenReturn("950S");
        when(inputVO.getMbrsh_pgm_id()).thenReturn("A");
        when(inputVO.getMbr_id()).thenReturn("222222222");
        when(inputVO.getPnt_knd_cd()).thenReturn("O11");
        when(mapper.mbr_mempnt_trn_tpcom_ei2001(any())).thenReturn(1);

        /* 서비스 호출 */
        ResponseEntity<MempntuptOutputVO> output = service.syncCall(headers, inputVO);

        /* 정상 호출여부 검증 */
        verify(service).syncCall(any(), any());
        verify(mapper).mbr_mempnt_trn_tpcom_ei2001(any());
    }

    @Test
    public void testMempntuptServiceMapperCall9080() {
        /* Mock object 선언 */
        HttpHeaders headers = mock(HttpHeaders.class);
        MempntuptService service = spy(MempntuptServiceImpl.class);
        MempntuptInputVO inputVO = mock(MempntuptInputVO.class);
        Mbr_mempnt_trnMapper mapper = mock(Mbr_mempnt_trnMapper.class);
        ((MempntuptServiceImpl) service).setMbr_mempnt_trnMapper(mapper);

        /* stub 선언*/
        when(inputVO.getCur_pnt()).thenReturn(0L);
        when(inputVO.getAvl_pnt()).thenReturn(0L);
        when(inputVO.getOrgan_cd()).thenReturn("950S");
        when(inputVO.getMbrsh_pgm_id()).thenReturn("A");
        when(inputVO.getMbr_id()).thenReturn("222222222");
        when(inputVO.getPnt_knd_cd()).thenReturn("O11");
        when(mapper.mbr_mempnt_trn_tpcom_ei2001(any())).thenReturn(0);

        /* 서비스 호출 */
        try {
            ResponseEntity<MempntuptOutputVO> output = service.syncCall(headers, inputVO);
        } catch (ValidException e) {
            if (e.getAns_cd().equals("9080")) {
                log.info("에러코드 : " + e.getAns_cd() + ". 에러 검증 완료");
            }else {
                throw new ValidException(headers, e.getAns_cd(), e.getAns_msg());
            }
        }

        /* 정상 호출여부 검증 */
        verify(service).syncCall(any(), any());
        verify(mapper).mbr_mempnt_trn_tpcom_ei2001(any());
    }
}
