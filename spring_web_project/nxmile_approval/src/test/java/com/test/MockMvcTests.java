package com.test;


import com.test.controller.TestController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@WebAppConfiguration    // WebApplicationContext를 생성할 수 있도록 하는 어노테이션
public class MockMvcTests {

    @Autowired
    private WebApplicationContext context; // MockMvc 객체 생성을 위한 context
    private MockMvc mockMvc;    // Controller 테스트를 위한 MockMvc

    @Autowired
    TestController testController;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(testController).build();     // test를 위한 MockMvc 객체 생성. testController 1개만 주입.
//        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).build();  // test를 위한 MockMvc 객체 생성. 스프링이 로드한 WebApplicationContext의 인스턴스로 작동
    }

    @Test
    public void test() throws Exception {
        mockMvc.perform(get("/test")        // controller의 /test URI를 get방식으로 호출
                .param("number1", "1")  // 파라미터 number1에 1 입력
                .param("number2", "1")) // 파라미터 number2에 1입력
                .andDo(print())                        // 결과를 print. MockMvcBuilders의 alwaysDo(print())로 대체 가능
                .andExpect(status().isOk());           // 호출 결과값이 OK가 나오면 정상처리
    }
}