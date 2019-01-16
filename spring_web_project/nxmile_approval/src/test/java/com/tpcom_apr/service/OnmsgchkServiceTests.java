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
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@WebAppConfiguration
@Log4j
public class OnmsgchkServiceTests {
    private WebApplicationContext context; // MockMvc 객체 생성을 위한 context
    private MockMvc mockMvc; // controller에 request를 수행해주는 mock 객체
    ObjectMapper mapper; // 객체를 json 형태로 변경시키는 객체

    @Mock
    OnmsgchkService onmsgchkService;
    @InjectMocks
    HomeController2 homeController2;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build(); // test를 위한 MockMvc 객체 생성
//        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController2).build();
        mapper = new ObjectMapper();
    }

    @Test
    public void testOnmsgchkServiceOK() throws Exception {
        mockMvc.perform(post("/onmsgchk")        // Controller의 /onmsgchk URI를 Post방식으로 호출
                .contentType(MediaType.APPLICATION_JSON_VALUE)    // contentType은 json 형식
                .content(mapper.writeValueAsString(         // 객체를 json로 변경. content에는 post의 body가 들어감.
                        new OnmsgchkInputVO(
                                "ZPTUTXPTC0001",
                                "K410",
                                "5004",
                                "9999999999",
                                "89000000", "","20190115","1111111111111111","","20190101","G88888888","",10000L,10000L,10000L,"Y","","","ON",""))))
                .andExpect(status().isOk())                 // 상태값은 OK가 나오면 정상처리
                .andDo(print());                            // 처리 내용을 출력
    }

    @Test
    public void testOnmsgchkServiceSvcModuIdError() throws Exception {
        mockMvc.perform(post("/onmsgchk")        // Controller의 /onmsgchk URI를 Post방식으로 호출
                .contentType(MediaType.APPLICATION_JSON_VALUE)    // contentType은 json 형식
                .content(mapper.writeValueAsString(         // 객체를 json로 변경. content에는 post의 body가 들어감.
                        new OnmsgchkInputVO(
                                "ZPTUTXPTC0001",
                                "K410",
                                "5004",
                                "9999999999",
                                "89000000", "","20190115","1111111111111111","","20190101","G88888888","",10000L,10000L,10000L,"Y","","","ON",""))))
                .andExpect(status().isOk())                 // 상태값은 OK가 나오면 정상처리
                .andExpect()
                .andDo(print());                            // 처리 내용을 출력
    }

}
