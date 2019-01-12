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
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
public class MockTests {

    @Mock
    OnmsgchkService onmsgchkService;

    @Mock
    OnmsgchkInputVO onmsgchkInputVO;

    @Test
    public void mockTest() {
        MockitoAnnotations.initMocks(this); // Mock 어노테이션을 사용한 변수들의 객체를 생성해줌.
        assertTrue(onmsgchkService!=null);
    }

    @Test   // 지정 메소드에 대한 반환값을 설정해줄 때 when() 사용
    public void whenTest() {
        MockitoAnnotations.initMocks(this);
        when(onmsgchkInputVO.getAns_cd1()).thenReturn("0000");
        when(onmsgchkInputVO.getOrgn_deal_amt()).thenReturn(10000L);
        assertTrue("0000".equals(onmsgchkInputVO.getAns_cd1()));
        assertTrue(10000L == onmsgchkInputVO.getOrgn_deal_amt());
    }

    @Test(expected = IllegalArgumentException.class) // 특정 조건에서 예외를 던지고 싶을 때는 doThrow() 사용
    public void doThrowTest() {
        MockitoAnnotations.initMocks(this);
        doThrow(new IllegalArgumentException()).when(onmsgchkInputVO).setAns_cd1(eq("0000"));
        String ans_cd="0000";
        onmsgchkInputVO.setAns_cd1(ans_cd);
    }

    @Test // void 로 선언된 메소드에 when을 걸어야 하는 경우 doNothing() 사용. verify()는 단순히 메소드가 호출되었는지 확인하는 용도임
    public void doNothtingTest() {
        MockitoAnnotations.initMocks(this);
        doNothing().when(onmsgchkInputVO).setAns_cd1(anyString());
        onmsgchkInputVO.setAns_cd1("0001");
        verify(onmsgchkInputVO).setAns_cd1(anyString());
    }

    @Test
    public void verfiyTest() {
        MockitoAnnotations.initMocks(this);
        String ans_cd = "1111";
        onmsgchkInputVO.setAns_cd1(ans_cd);

        // n번 호출했는지 체크
        verify(onmsgchkInputVO, times(1)).setAns_cd1(anyString()); // success
        verify(onmsgchkInputVO, times(1)).setAns_cd1(any(String.class)); // success
//        verify(onmsgchkInputVO, times(2)).setAns_cd1(any(String.class)); // fail

        // 호출 했는지 안했는지 체크
//        verify(onmsgchkInputVO, never()).setAns_cd1(anyString()); // fail
        verify(onmsgchkInputVO, never()).setAns_cd1(eq("1111")); // fail
        verify(onmsgchkInputVO, never()).setAns_cd1(eq("0000")); // success
        verify(onmsgchkInputVO, never()).getOrgn_deal_amt(); // success

        // 최소한 한번 이상 호출했는지 체크
        verify(onmsgchkInputVO, atLeastOnce()).setAns_cd1(anyString()); // success

        // 2번 이하 호출했는지 체크
        verify(onmsgchkInputVO, atMost(2)).setAns_cd1(any()); // success

        // 2번 이상 호출했는지 체크
//        verify(onmsgchkInputVO, atLeast(2)).setAns_cd1(any(String.class)); // fail

        // 지정된 시간(millis) 안으로 메소드를 호출했는지 체크
        verify(onmsgchkInputVO, timeout(100)).setAns_cd1(any(String.class)); // success

        // 지정된 시간(millis) 안으로 1번 이상 메소드를 호출했는지 체크
        verify(onmsgchkInputVO, timeout(100).atLeastOnce()).setAns_cd1(anyString()); // success
    }
}
