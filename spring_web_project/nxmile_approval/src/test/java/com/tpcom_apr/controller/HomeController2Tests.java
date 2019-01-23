package com.tpcom_apr.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.mapper.Rul_svcavl_conMapper;
import com.tpcom_apr.service.OnmsgchkServiceImpl;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@WebAppConfiguration
@Log4j
public class HomeController2Tests {

    private MockMvc mockMvc; // controller에 request를 수행해주는 mock 객체
    ObjectMapper mapper; // 객체를 json 형태로 변경시키는 객체

    @Autowired
    private WebApplicationContext context; // MockMvc 객체 생성을 위한 context
    @Setter(onMethod_ = {@Autowired})
    private Rul_svcavl_conMapper rul_svcavl_conMapper;
    @Autowired
    private OnmsgchkServiceImpl onmsgchkService;


    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).alwaysDo(print()).build(); // test를 위한 MockMvc 객체 생성.  MockMvc로 테스트 시 print() 출력
//        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController2).build();
        mapper = new ObjectMapper();
    }

    // 정상처리 테스트
    @Test
    public void testOnmsgchkControllerOK() throws Exception {

        OnmsgchkInputVO inputVO = getOnmsgchkInputVO("0000");   // 테스트 입력값 객체 생성
        mockMvc.perform(post("/onmsgchk")                  // Controller의 /onmsgchk URI를 Post방식으로 호출
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)     // contentType은 json 형식
                .content(mapper.writeValueAsString(inputVO)))           // 객체를 json로 변경. content에는 post의 body가 들어감.
                .andExpect(status().isOk())                             // 상태값은 OK가 나오면 정상처리
                .andExpect(header().string("ans_cd", "0000"))
                .andExpect(jsonPath("$.mbrsh_pgm_id").value("A"))
                .andExpect(jsonPath("$.telgrm_typ").isNotEmpty())
                .andExpect(jsonPath("$.ans_cd").value("0000"))
                .andExpect(jsonPath("$.msg_fg").isNotEmpty());
//                .andDo(print());                              // 처리 내용을 출력
    }

    // 7777 추적번호 미유입 에러 테스트
    @Test
    public void testOnmsgchkControllerTrcNoInputError() throws Exception {
        OnmsgchkInputVO inputVO = getOnmsgchkInputVO("7777");
        mockMvc.perform(post("/onmsgchk")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content(mapper.writeValueAsString(inputVO)))
                .andExpect(status().isOk())
                .andExpect(header().string("ans_cd", "7777"));
    }


    // 테스트 입력값을 생성해주는 함수
    public OnmsgchkInputVO getOnmsgchkInputVO(String errcode) {
        switch (errcode) {
            case "0000":
                return new OnmsgchkInputVO(
                        "ZPTUTXPTC0001", "K410", "5004", "9999999999",
                        "89000000", "", "20190115", "1111111111111111",
                        "", "20190101", "G88888888", "",
                        10000L, 10000L, 10000L, "Y",
                        "", "", "ON", "");
            case "7777":
                return new OnmsgchkInputVO(
                        "ZPTUTXPTC0001", "K410", "5004", "",
                        "89000000", "", "20190115", "1111111111111111",
                        "", "20190101", "G88888888", "",
                        10000L, 10000L, 10000L, "Y",
                        "", "", "ON", "");
            default:
                return null;
        }

    }

}
