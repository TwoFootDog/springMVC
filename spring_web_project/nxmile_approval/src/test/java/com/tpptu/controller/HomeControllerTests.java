package com.tpptu.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpptu.domain.ZptutxptcInputVO;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
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
public class HomeControllerTests {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    HomeController homeController;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;


    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.standaloneSetup(homeController).build();
        objectMapper = new ObjectMapper();
    }

    @Test
    public void TestZptutxptcCallOk() throws Exception {
        ZptutxptcInputVO inputVO = getZptutxptcInputVO("0000");
        HttpHeaders headers = new HttpHeaders();
        headers.add("telgrm_no", "K410");
        headers.add("organ_cd", "950S");
        headers.add("send_dy", "20190129");
        headers.add("send_tm", "143000");
        headers.add("trc_no", "1111111111");
        headers.add("telgrm_fg", "ON");
        headers.add("data_size", "600");
        headers.add("ans_cd1", "");
        headers.add("ans_cd2", "");
        headers.add("filler", "");
        mockMvc.perform(post("/zptutxptc")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .headers(headers)
                .content(objectMapper.writeValueAsString(inputVO)))
//                .andExpect(status().isOk())
//                .andExpect(header().string("ans_cd1", "00"))
//                .andExpect(header().string("ans_cd2", "00"))
//                .andExpect(jsonPath("$.mbrsh_pgm_id").value("A"))
                .andDo(print());

    }

    public ZptutxptcInputVO getZptutxptcInputVO(String ansCd) {
        if (ansCd.equals("0000")) {
            return new ZptutxptcInputVO(
                    1L, "A", "A1", "", "",
                    "", "89004518", "1111111111", "20190129", "143000",
                    "2200111133331352", "", "20190103", "F88405584", "",
                    "", "", "", "", "22",
                    "42", "", 10000L, "1", "",
                    "", "");
        } else {
            return null;
        }
    }
}
