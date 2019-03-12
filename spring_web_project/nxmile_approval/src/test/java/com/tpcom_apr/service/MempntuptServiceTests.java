package com.tpcom_apr.service;

import com.commons.domain.CustomizeHeaderVO;
import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.MempntuptInputVO;
import com.tpcom_apr.domain.service.wrapper.MempntuptInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.MempntuptOutputWrapperVO;
import com.tpcom_apr.mapper.Mbr_mempnt_trnMapper;
import com.tpcom_apr.service.serviceInterface.MempntuptService;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
//@Transactional
public class MempntuptServiceTests {

    @Test
    public void testMempntuptServiceSyncCallOk() {
        /* Mock object 선언 */
        MempntuptService service = mock(MempntuptServiceImpl.class);

        /* 서비스 호출*/
        MempntuptOutputWrapperVO output = service.syncCall(new MempntuptInputWrapperVO());

        /* 정상 호출여부 검증 */
        verify(service).syncCall(any());
    }

    @Test
    public void testMempntuptServiceMapperCallOk() {
        /* Mock object 선언 */
        MempntuptService service = spy(MempntuptServiceImpl.class);
        CustomizeHeaderVO header = mock(CustomizeHeaderVO.class);
        MempntuptInputWrapperVO inputWrapperVO = mock(MempntuptInputWrapperVO.class);
        MempntuptInputVO inputVO = mock(MempntuptInputVO.class);
        Mbr_mempnt_trnMapper mapper = mock(Mbr_mempnt_trnMapper.class);
        ((MempntuptServiceImpl) service).setMbr_mempnt_trnMapper(mapper);

        /* stub 선언*/
        when(inputWrapperVO.getHeader()).thenReturn(header);
        when(inputWrapperVO.getBody()).thenReturn(inputVO);
        when(header.getTelgrm_no()).thenReturn("K410");
        when(inputVO.getCur_pnt()).thenReturn(0L);
        when(inputVO.getAvl_pnt()).thenReturn(0L);
        when(inputVO.getOrgan_cd()).thenReturn("950S");
        when(inputVO.getMbrsh_pgm_id()).thenReturn("A");
        when(inputVO.getMbr_id()).thenReturn("222222222");
        when(inputVO.getPnt_knd_cd()).thenReturn("O11");
        when(mapper.mbr_mempnt_trn_tpcom_ei2001(any())).thenReturn(1);

        /* 서비스 호출 */
        MempntuptOutputWrapperVO output = service.syncCall(inputWrapperVO);

        /* 정상 호출여부 검증 */
        verify(service).syncCall(any());
        verify(mapper).mbr_mempnt_trn_tpcom_ei2001(any());
    }

    @Test
    public void testMempntuptServiceMapperCall9080() {
        /* Mock object 선언 */
        MempntuptService service = spy(MempntuptServiceImpl.class);
        MempntuptInputWrapperVO inputWrapperVO = mock(MempntuptInputWrapperVO.class);
        MempntuptInputVO inputVO = mock(MempntuptInputVO.class);
        Mbr_mempnt_trnMapper mapper = mock(Mbr_mempnt_trnMapper.class);
        ((MempntuptServiceImpl) service).setMbr_mempnt_trnMapper(mapper);

        /* stub 선언*/
        when(inputWrapperVO.getBody()).thenReturn(inputVO);
        when(inputVO.getCur_pnt()).thenReturn(0L);
        when(inputVO.getAvl_pnt()).thenReturn(0L);
        when(inputVO.getOrgan_cd()).thenReturn("950S");
        when(inputVO.getMbrsh_pgm_id()).thenReturn("A");
        when(inputVO.getMbr_id()).thenReturn("222222222");
        when(inputVO.getPnt_knd_cd()).thenReturn("O11");
        when(mapper.mbr_mempnt_trn_tpcom_ei2001(any())).thenReturn(0);

        /* 서비스 호출 */
        try {
            MempntuptOutputWrapperVO output = service.syncCall(inputWrapperVO);
        } catch (ValidException e) {
            if (e.getAns_cd().equals("9080")) {
                log.info("에러코드 : " + e.getAns_cd() + ". 에러 검증 완료");
            }else {
                throw new ValidException(e.getAns_cd(), e.getAns_msg());
            }
        }

        /* 정상 호출여부 검증 */
        verify(service).syncCall(any());
        verify(mapper).mbr_mempnt_trn_tpcom_ei2001(any());
    }


    // 테이블 삭제 후 테스트 해야함
    @Autowired
    ApplicationContext ctx;
    @Test
    public void testMempntuptServiceTableNotFoundException() {

        MempntuptService service = spy(MempntuptServiceImpl.class);
        Mbr_mempnt_trnMapper mapper = ctx.getBean(Mbr_mempnt_trnMapper.class);

        ((MempntuptServiceImpl) service).setMbr_mempnt_trnMapper(mapper);
        MempntuptInputWrapperVO inputWrapperVO = mock(MempntuptInputWrapperVO.class);
        MempntuptInputVO inputVO = mock(MempntuptInputVO.class);

        /* stub 선언*/
        when(inputWrapperVO.getBody()).thenReturn(inputVO);
        when(inputVO.getCur_pnt()).thenReturn(0L);
        when(inputVO.getAvl_pnt()).thenReturn(0L);
        when(inputVO.getOrgan_cd()).thenReturn("950S");
        when(inputVO.getMbrsh_pgm_id()).thenReturn("A");
        when(inputVO.getMbr_id()).thenReturn("222222222");
        when(inputVO.getPnt_knd_cd()).thenReturn("O11");

        /* 서비스 호출 */
        try {
            MempntuptOutputWrapperVO output = service.syncCall(inputWrapperVO);
        } catch (ValidException e) {
            if (e.getAns_cd().equals("9080")) {
                log.info("에러코드 : " + e.getAns_cd() + ". 에러 검증 완료");
            }else {
                throw new ValidException(e.getAns_cd(), e.getAns_msg());
            }
        }
    }
}
