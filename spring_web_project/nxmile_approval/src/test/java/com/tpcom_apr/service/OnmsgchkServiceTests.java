package com.tpcom_apr.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tpcom_apr.controller.HomeController2;
import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.Rul_svcavl_con_tpcom_vs2001OutputVO;
import com.tpcom_apr.mapper.Rul_svcavl_conMapper;
import com.tpcom_apr.service.service_interface.OnmsgchkService;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml", "file:src/main/webapp/WEB-INF/spring-config/dispatcher-servlet.xml"})
@WebAppConfiguration
@Log4j
public class OnmsgchkServiceTests {

    @Autowired
    private WebApplicationContext context; // MockMvc 객체 생성을 위한 context
    @Mock
    Rul_svcavl_conMapper rul_svcavl_conMapper;
    @Spy
    OnmsgchkServiceImpl onmsgchkService;

    private MockMvc mockMvc; // controller에 request를 수행해주는 mock 객체
    ObjectMapper mapper; // 객체를 json 형태로 변경시키는 객체
    Rul_svcavl_con_tpcom_vs2001OutputVO rul_svcavl_con_tpcom_vs2001OutputVO;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.context).alwaysDo(print()).build(); // test를 위한 MockMvc 객체 생성.  MockMvc로 테스트 시 print() 출력
        mapper = new ObjectMapper();
    }

    @Test
    public void testOnmsgchkServiceOK1() throws Exception {
        OnmsgchkInputVO inputVO = new OnmsgchkInputVO(
                "ZPTUTXPTC0001", "K410", "5004", "9999999999",
                "89000000", "", "20190115", "1111111111111111",
                "", "20190101", "G88888888", "",
                10000L, 10000L, 10000L, "Y",
                "", "", "ON", "");
        onmsgchkService.syncCall(any(), inputVO);
        verify(onmsgchkService).commonInputDataValidChk(inputVO);

    }

//    @Test
//    public void testOnmsgchkServiceMchtBizNoLengthtError() throws Exception {
//        when(rul_svcavl_con_tpcom_vs2001OutputVO.getCncl_yn()).thenReturn("N");
//        mockMvc.perform(post("/onmsgchk")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(mapper.writeValueAsString(
//                        new OnmsgchkInputVO(
//                                "ZPTUTXPTC0001", "K410",
//                                "5004", "9999999999",
//                                "89000000", "1111",
//                                "20190115","1111111111111111",
//                                "","20190101",
//                                "G88888888","",
//                                10000L,10000L,
//                                10000L,"Y",
//                                "","",
//                                "ON",""))))
//                .andExpect(status().isOk())
//                .andExpect(header().string("ans_cd", "3311"))
//                .andDo(print());
//    }
//
//    // 정상처리 테스트
//    @Test
//    public void testOnmsgchkServiceOK() throws Exception {
//        mockMvc.perform(post("/onmsgchk")        // Controller의 /onmsgchk URI를 Post방식으로 호출
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)    // contentType은 json 형식
//                .content(mapper.writeValueAsString(         // 객체를 json로 변경. content에는 post의 body가 들어감.
//                        new OnmsgchkInputVO(
//                                "ZPTUTXPTC0001",
//                                "K410",
//                                "5004",
//                                "9999999999",
//                                "89000000", "","20190115","1111111111111111","","20190101","G88888888","",10000L,10000L,10000L,"Y","","","ON",""))))
//                .andExpect(status().isOk())                 // 상태값은 OK가 나오면 정상처리
//                .andExpect(header().string("ans_cd", "0000"))
//                .andExpect(jsonPath("$.mbrsh_pgm_id").value("A"))
//                .andExpect(jsonPath("$.telgrm_typ").isNotEmpty())
//                .andExpect(jsonPath("$.ans_cd").value("0000"))
//                .andExpect(jsonPath("$.msg_fg").isNotEmpty());
////                .andDo(print());                            // 처리 내용을 출력
//    }
//
//    // 7777 추적번호 미유입 에러 테스트
//    @Test
//    public void testOnmsgchkServiceTrcNoInputError() throws Exception {
//        mockMvc.perform(post("/onmsgchk")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(mapper.writeValueAsString(
//                        new OnmsgchkInputVO(
//                                "ZPTUTXPTC0001", "K410",
//                                "5004", "",
//                                "89000000", "",
//                                "20190115","1111111111111111",
//                                "","20190101",
//                                "G88888888","",
//                                10000L,10000L,
//                                10000L,"Y",
//                                "","",
//                                "ON",""))))
//                .andExpect(status().isOk())
//                .andExpect(header().string("ans_cd", "7777"));
//    }
//
//    // 7730 가맹점번호 미유입 에러 테스트
//    @Test
//    public void testOnmsgchkServiceNoMchtNoInputError() throws Exception {
//        mockMvc.perform(post("/onmsgchk")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(mapper.writeValueAsString(
//                        new OnmsgchkInputVO(
//                                "ZPTUTXPTC0001", "K410",
//                                "5004", "9999999999",
//                                "", "",
//                                "20190117", "1111111111111111",
//                                "", "20190101",
//                                "F88888888", "",
//                                0L, 0L,
//                                0L,"",
//                                "","",
//                                "",""))))
//                .andExpect(status().isOk())
//                .andExpect(header().string("ans_cd","7730"));
//    }
//
//    // 6940 거래일자 미 유입 에러 테스트
//    @Test
//    public void testOnmsgChkServiceNoDealDyInputError() throws Exception {
//        mockMvc.perform(post("/onmsgchk")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(mapper.writeValueAsString(
//                        new OnmsgchkInputVO(
//                                "ZPTUTXPTC0001", "K410",
//                                "5004", "9999999999",
//                                "89009974", "",
//                                "", "1111111111111111",
//                                "", "20190101",
//                                "F88888888", "",
//                                0L, 0L,
//                                0L,"",
//                                "","",
//                                "",""))))
//                .andExpect(status().isOk())
//                .andExpect(header().string("ans_cd","6940"));
//    }
//
//    // 7720 식별자번호 미유입 에러 체크
//    @Test
//    public void tesetOnmsgChkServiceNoCrdNoInputError() throws Exception {
//        mockMvc.perform(post("/onmsgchk")
//                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
//                .content(mapper.writeValueAsString(
//                        new OnmsgchkInputVO(
//                                "ZPTUTXPTC0001", "K410",
//                                "5004", "9999999999",
//                                "89009974", "",
//                                "20190117", "",
//                                "", "20190101",
//                                "F88888888", "",
//                                0L, 0L,
//                                0L,"",
//                                "","",
//                                "",""))))
//                .andExpect(status().isOk())
//                .andExpect(header().string("ans_cd","7720"));
//    }

}
