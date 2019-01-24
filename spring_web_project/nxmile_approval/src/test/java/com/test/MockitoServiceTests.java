package com.test;


import com.test.dao.TestDao;
import com.test.domain.BoardVO;
import com.test.mapper.TestMapper;
import com.test.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.util.StringUtils;

import java.util.Date;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@WebAppConfiguration
public class MockitoServiceTests {

    @Mock
    private TestService testService;


    BoardVO boardVO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);     // Mock으로 지정된 객체 생성
    }

    @Test
    public void testService() {
        assertNotNull(testService);
        when(testService.getBoardInfo(10L)).thenReturn(new BoardVO(10L, "title1", "content1", "writer1", new Date(), new Date()));
        boardVO = testService.getBoardInfo(10L);
        verify(testService, atLeastOnce()).serviceBefore();
//        verify(testService, atLeast(2)).serviceBefore();
    }

}


