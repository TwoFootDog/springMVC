package com.test;


import com.test.service.TestService;
import lombok.Setter;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static java.lang.Thread.sleep;
import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJunit4ClassRunner.class는 spring-test에서 제공하는 단위테스르를 위한 클래스 러너
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml") // 테스트 시 참조할 설정파일
public class JUnitTests {

    @Setter(onMethod_ = {@Autowired})
    private TestService testService;    // 테스트 대상 Service

    @Before
    public void setUp() {
        // 테스트 시작 전 호출
    }
    @After
    public void finish() {
        // 테스트 완료 후 호출
    }
    @Test   // JUnit 테스트 대상
    public void testNotNull() {
        assertNotNull(testService.numberCompare(1,1));  // 함수 호출 결과가 null이 아니면 정상. null이면 에러
    }
    @Test
    public void testNull() {
        assertNull(testService);    // 함수 호출 결과가 null이면 정상. null이 아니면 에러
    }
    @Test
    public void testEqual() {
        assertEquals(testService.numberCompare(1,2), false);    // 함수 호출 결과가 false와 같으면 정상. 다르면 에러
    }
    @Test
    public void testSame() {
        assertSame(testService, testService);   // 두 객체가 같은 객체이면 정상. 다르면 에러
    }
    @Test
    public void testTrue() {
        assertTrue(testService.numberCompare(1,1));     // 함수 호출 결과가 true를 리턴하면 정상. false면 에러
    }
    @Test
    public void testTrue2() {
        assertTrue(testService.numberCompare(2,1));     // 함수 호출 결과가 false 이므로 에러
    }
    @Test(expected = RuntimeException.class)            // RuntimeExcepion 이 발생할 경우 정상. 미 발생 시 에러
    public void runtimeExceptionTest() {
        throw new RuntimeException();
    }
    @Test(timeout=1000)                                 // Test timeout을 1000밀리초로 설정. 1000밀리초 이상 지연될 경우 에러
    public void sleepTest() throws Exception{
        sleep(10000);
    }
    @Test
    @Ignore         // 테스트를 skip하는 어노테이션
    public void ignoreTest() {
        assertTrue(1==2);
    }
}
