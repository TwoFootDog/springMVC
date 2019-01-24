package com.test;


import com.test.dao.TestDao;
import com.test.domain.BoardVO;
import com.test.service.TestService;
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

import static junit.framework.TestCase.assertNotNull;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.*;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@WebAppConfiguration
@Log4j
public class MockitoDaoTests {

    @Mock
    private TestDao testDao;

    @InjectMocks
    private TestService testService;

    BoardVO boardVO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);     // Mock으로 지정된 객체 생성
    }

    @Test
    public void testDao() {
        assertNotNull(testDao);
        doNothing().when(testDao).beforeFunction();
        when(testDao.getBoardInfo(1L)).thenReturn(new BoardVO(1L,"title1","content1","writer1", new Date() , new Date()));

        boardVO = testService.getBoardInfo(1L);

        log.info(boardVO.getContent());
//        verify(testService).getBoardInfo(anyLong());
//        verify(testDao).beforeFunction();
//
//        verify(testDao).beforeFunction();
//        verify(testDao).afterFunction();
    }

//    @Test
//    public void testService() {
//        assertNotNull(testService);
//        boardVO = testService.getBoardInfo(10L);
//        assertTrue(StringUtils.isEmpty(boardVO));
//    }

}


