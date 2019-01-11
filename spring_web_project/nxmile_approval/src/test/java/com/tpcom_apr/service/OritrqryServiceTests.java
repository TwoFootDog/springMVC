package com.tpcom_apr.service;


import com.commons.exception.GlobalExceptionHandler;
import com.commons.exception.ValidException;
import com.tpcom_apr.domain.OritrqryInputVO;
import com.tpcom_apr.domain.OritrqryOutputVO;
import com.tpcom_apr.service.service_interface.OritrqryService;
import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.method.annotation.ExceptionHandlerMethodResolver;
import org.springframework.web.servlet.mvc.method.annotation.ExceptionHandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ServletInvocableHandlerMethod;

import javax.servlet.http.HttpServletRequest;

import java.lang.reflect.Method;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring-config/applicationContext.xml")
@Log4j
public class OritrqryServiceTests {

    @Mock
    private OritrqryService oritrqryService;
    private MockHttpServletRequest request;

    @Before
    public void beforeTest() {
        MockMvc mockMvc = standaloneSetup(oritrqryService)
                .setHandlerExceptionResolvers(createExceptionResolver())
                .build();
    }

    private ExceptionHandlerExceptionResolver createExceptionResolver() {
        ExceptionHandlerExceptionResolver exceptionResolver = new ExceptionHandlerExceptionResolver() {
            protected ServletInvocableHandlerMethod getExceptionHandlerMethod(HandlerMethod handlerMethod, Exception exception) {
                Method method = new ExceptionHandlerMethodResolver(GlobalExceptionHandler.class).resolveMethod(exception);
                return new ServletInvocableHandlerMethod(new GlobalExceptionHandler(), method);
            }
        };
        exceptionResolver.afterPropertiesSet();
        return exceptionResolver;
    }

//    @Test(expected = ValidException.class)
    @Test
    public void oritrqryTest() {

        request = new MockHttpServletRequest();
        OritrqryInputVO oritrqryInputVO =
                new OritrqryInputVO("ZPTUTXPTC0001",
                        "9999999999",
                        "A",
                        "89004517",
                        "2200111133331352",
                        "20190103",
                        "F88405584",
                        "",
                        10000L,
                        "",
                        "");
        request.addHeader("organ_cd", "5004");
        ResponseEntity<OritrqryOutputVO> oritrqryOutputVO = oritrqryService.syncCall(request, oritrqryInputVO);
    log.info("oritrqryOutputVO : " + oritrqryOutputVO);
    }
}
