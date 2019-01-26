package com.test;


import com.test.dao.TestDao;
import com.test.domain.BoardVO;
import com.test.service.TestService2;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Date;

import static junit.framework.TestCase.assertTrue;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@WebAppConfiguration
@Log4j
public class MockitoTests {

//    @Mock
//    TestDao dao;
//    @Spy           // @Spy는 InjectMocks와 동시 사용 가능
//    @InjectMocks
//    TestService2 service;
//    @Mock
//    TestService2 service;

//    @Before
//    public void setUp() {
//        MockitoAnnotations.initMocks(this);     // Mock으로 지정된 객체 생성
//    }


    @Test
    public void testService() {
        TestService2 service = mock(TestService2.class);

        BoardVO boardVO = service.getBoardInfo(10L);
        verify(service).getBoardInfo(10L);
    }

    @Test
    public void testDao() {
        TestDao dao = mock(TestDao.class);          // mock 객체 생성
        TestService2 service = new TestService2();  // 실제 객체 생성
        service.setDao(dao);                        // mock 객체를 실제 객체에 주입

        when(dao.getBoardInfo(1L)).thenReturn(new BoardVO(1L, "title1", "content1", "writer1", new Date(), new Date()));    // 결과값 지정(stub)
        BoardVO boardVO = service.getBoardInfo(1L); // 서비스의 getBoardInfo() 메소드 호출
        verify(dao).getBoardInfo(1L);               // // mock객체 dao의 메소드인 getBoardInfo()가 정상 호출되었는지 검증
    }

    @Test
    public void testDaoSpy2() {
        TestService2 service = spy(TestService2.class);     // spy 객체 생성
        TestDao dao = mock(TestDao.class);                  // mock 객체 생성
        service.setDao(dao);                                // mock 객체를 spy 객체에 주입

        when(dao.getBoardInfo(10L)).thenReturn(new BoardVO(1L, "title1", "content1", "writer1", new Date(), new Date()));   // 결과값 지정(stub)
        BoardVO boardVO = service.getBoardInfo(10L);    // 서비스의 getBoardInfo() 메소드 호출
        verify(service).getBoardInfo(10L);              // 서비스의 실제 메소드인 getBoardInfo() 가 정상 호출되었는지 검증
        verify(service).serviceBefore();                     // 서비스의 실제 메소드인 serviceBefore() 가 정상 호출되었는지 검증
        verify(dao).getBoardInfo(10L);                  // mock객체 dao의 메소드인 getBoardInfo()가 정상 호출되었는지 검증
    }


    @Test
    public void testWhen() {
        TestService2 service = mock(TestService2.class);
        when(service.getAge(1)).thenReturn(2);
        assertTrue(service.getAge(1) == 2);
    }

    @Test
    public void testDoThrow() {
        TestService2 service = mock(TestService2.class);
        doThrow(new IllegalArgumentException()).when(service).getAge(1);
        assertTrue(service.getAge(1) == 2);
    }

    @Test
    public void testDoNothing() {
        TestService2 service = mock(TestService2.class);
        doNothing().when(service).setAge(10);
        service.setAge(10);
    }

    @Test
    public void testVerify() {
        TestService2 service = mock(TestService2.class);        // mock 객체 생성
        BoardVO board = service.getBoardInfo(10L);         // mock 객체 함수 호출(argument 10L)

        verify(service).getBoardInfo(10L);                 // 해당 함수가 argument 10L로 호출된 적이 있는지 검증. 정상처리
        verify(service, times(1)).getBoardInfo(10L);        // 해당 함수가 argument 10L로 1번 호출된 적이 있는지 검증. 정상 처리
        verify(service, times(1)).getBoardInfo(anyLong());       // 해당 함수가 argument Long 타입으로 호출된 적이 있는지 검증. 정상 처리
        verify(service, atLeastOnce()).getBoardInfo(10L);                           // 해당 함수가 argument 10L로 최소 한번 호출된 적이 있는지 검증. 정상 처리
        verify(service, atMost(2)).getBoardInfo(anyLong());        // 해당 함수가 argument Long 타입으로 호출된 적이 최대 2번인지 검증. 정상 처리
        verify(service, timeout(100)).getBoardInfo(10L);                      // 해당 함수가 100 밀리초 이내에 호출디 되었는지 검증. 정상처리
        //verify(service, never()).getBoardInfo(anyLong());                              // 해당 함수가 argument Long 타입으로 호출된 적이 없는지 검증. 에러 처리
//        verify(service, never()).getBoardInfo(10L);                                    // 해당 함수가 argument 10L로 호출된 적이 없는지 검증. 에러 처리
        verify(service, never()).getBoardInfo(1L);                                  // 해당 함수가 argument 1L로 호출된 적이 없는지 검증. 정상 처리
    }

}


