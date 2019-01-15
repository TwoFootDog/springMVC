package com.tpcom_apr.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpcom_apr.controller.HomeController2;
import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class) // SpringJunit4ClassRunner.class는 spring-test에서 제공하는 단위테스르를 위한 클래스 러너
@WebAppConfiguration    // WebApplicationContext를 생성할 수 있도록 하는 어노테이션
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@Log4j
public class MockMvcTests {
    @Autowired
    private WebApplicationContext context;
    private MockMvc mockMvc;    // controller에 request를 수행해주는 mock 객체
    ObjectMapper mapper; // 객체를 json 형식으로 변경 시 사용

    @Mock
    OnmsgchkService onmsgchkService;

    @InjectMocks
    HomeController2 homeController2;

    @Before // @Test 이전에 실행
    public void setUp() {
        MockitoAnnotations.initMocks(this); // @Mock이 등록된 서비스 객체 생성
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build(); // test를 위한 MockMvc 객체 생성
//        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController2).build();
        mapper = new ObjectMapper();

    }

    @Test
    public void onmsgChkTests() throws Exception {
        doNothing().when(onmsgchkService).commonInputDataValidChk(any());
        mockMvc.perform(post("/onmsgchk")        // Controller의 /onmsgchk URI를 Post방식으로 호출
                .contentType(MediaType.APPLICATION_JSON_VALUE)    // contentType은 json 형식
                .content(mapper.writeValueAsString(         // 객체를 json로 변경. content에는 post의 body가 들어감.
                        new OnmsgchkInputVO(
                                "ZPTUTXPTC0001",
                                "K410",
                                "5004",
                                "9999999999",
                                "89000000", "","20190115","1111111111111111","","","","",10000L,10000L,10000L,"","","","",""))))
                .andExpect(status().isOk())                 // 상태값은 OK가 나오면 정상처리
                .andDo(print());                            // 처리 내용을 출력

//        verifyZeroInteractions(onmsgchkService);
        verify(onmsgchkService, times(1)).commonInputDataValidChk(any());
//        verifyNoMoreInteractions(onmsgchkService);
    }
//
//    @Test
//    public void homeTests() throws Exception {
//        log.info(mockMvc.perform(get("/")).
//                andExpect(status().isOk()).
//                andDo(print()).
//                andReturn());
//    }


}
