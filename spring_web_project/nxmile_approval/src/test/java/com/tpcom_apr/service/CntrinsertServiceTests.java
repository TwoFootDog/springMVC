package com.tpcom_apr.service;

import com.commons.exception.ValidException;
import com.tpcom_apr.domain.service.CntrinsertInputVO;
import com.tpcom_apr.domain.service.wrapper.CntrinsertInputWrapperVO;
import com.tpcom_apr.domain.service.wrapper.CntrinsertOutputWrapperVO;
import com.tpcom_apr.mapper.Apr_dealtr_trnMapper;
import com.tpcom_apr.service.service_interface.CntrinsertService;
import lombok.extern.log4j.Log4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
@Transactional
public class CntrinsertServiceTests {

    @Test
    public void testCntrinsertServiceCallOK() {
        /* Mock object 선언 */
//        HttpHeaders headers = mock(HttpHeaders.class);
        CntrinsertService service = mock(CntrinsertServiceImpl.class);

        /* 서비스 호출 */
        CntrinsertOutputWrapperVO outputWrapperVO = service.syncCall(new CntrinsertInputWrapperVO());

        /* verify */
        verify(service).syncCall(any());
    }

    @Test
    public void testCntrinsertServiceMapperCallOK() {
        /* Mock Object 선언 */
        CntrinsertService service = spy(CntrinsertServiceImpl.class);
        CntrinsertInputWrapperVO inputWrapperVO = mock(CntrinsertInputWrapperVO.class);
        CntrinsertInputVO inputVO = mock(CntrinsertInputVO.class);
        Apr_dealtr_trnMapper mapper = mock(Apr_dealtr_trnMapper.class);
        ((CntrinsertServiceImpl) service).setApr_dealtr_trnMapper(mapper);

        /* Stub 선언 */
//        when(inputWrapperVO.getBody()).thenReturn(inputVO);
        when(mapper.apr_dealtr_trn_tpcom_ei2001(any())).thenReturn(1);


        /* 서비스 호출 */
        CntrinsertOutputWrapperVO outputWrapperVO = service.syncCall(inputWrapperVO);

        /* 검증 */
        verify(service).syncCall(any());
        verify(mapper).apr_dealtr_trn_tpcom_ei2001(any());
    }


    // 사전조건 :  data dup error OR 거래내역 테이블 미존재
    @Autowired
    ApplicationContext ctx;
    @Test
    public void testCntrinsertServiceDataDupException() {

        /* Mock 선언 */
        CntrinsertService service = spy(CntrinsertServiceImpl.class);
        CntrinsertInputWrapperVO inputWrapperVO = mock(CntrinsertInputWrapperVO.class);
        Apr_dealtr_trnMapper mapper = ctx.getBean(Apr_dealtr_trnMapper.class); // 실제 mapper 호출을 하기 위함.
        ((CntrinsertServiceImpl) service).setApr_dealtr_trnMapper(mapper);

        when(inputWrapperVO.getBody()).thenReturn(mock(CntrinsertInputVO.class));
        when(inputWrapperVO.getBody().getMbrsh_pgm_id()).thenReturn("A");
        when(inputWrapperVO.getBody().getAprv_dy()).thenReturn("20190208");
        when(inputWrapperVO.getBody().getAprv_no()).thenReturn("F88888888");
        when(inputWrapperVO.getBody().getCrd_no()).thenReturn("1111111111111111");


        try {
            CntrinsertOutputWrapperVO outputWrapperVO = service.syncCall(inputWrapperVO);
        } catch (ValidException e) {
            if (e.getAns_cd().equals("9080")) {
                log.info("에러코드 : " + e.getAns_cd() + ". 에러 메시지 : " + e.getAns_msg() + ". 에러 검증 완료");
            } else {
                throw new ValidException(e.getAns_cd(), e.getAns_msg());
            }
        } finally {
            verify(service).syncCall(any());
        }
    }

    // 사전조건 : 업데이트 되는 데이터가 미존재
    @Test
    public void testCntrinsertServiceDataNotFoundException() {
        CntrinsertService service = spy(CntrinsertServiceImpl.class);
        CntrinsertInputWrapperVO inputWrapperVO = mock(CntrinsertInputWrapperVO.class);
        Apr_dealtr_trnMapper mapper = ctx.getBean(Apr_dealtr_trnMapper.class);
        ((CntrinsertServiceImpl) service).setApr_dealtr_trnMapper(mapper);

        when(inputWrapperVO.getBody()).thenReturn(mock(CntrinsertInputVO.class));
        when(inputWrapperVO.getBody().getMbrsh_pgm_id()).thenReturn("A");
        when(inputWrapperVO.getBody().getAprv_dy()).thenReturn("20190209");
        when(inputWrapperVO.getBody().getAprv_no()).thenReturn("F88888893");
        when(inputWrapperVO.getBody().getCrd_no()).thenReturn("1111111111111117");

        try {
            CntrinsertOutputWrapperVO outputWrapperVO = service.syncCall(inputWrapperVO);
        } catch (ValidException e) {
            if (e.getAns_cd().equals("9080")) {
                log.info("에러코드 : " + e.getAns_cd() + ". 에러 메시지 : " + e.getAns_msg() + ". 에러 검증 완료");
            } else {
                throw new ValidException(e.getAns_cd(), e.getAns_msg());
            }
        } finally {
            verify(service).syncCall(any());
        }
    }
}
