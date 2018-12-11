package org.zerock.controller;

import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml",
        "file:src/main/webapp/WEB-INF/spring/appServlet/servlet-context.xml"})
@WebAppConfiguration // Test for the controller
@Log4j
public class BoardControllerTests {

    @Setter(onMethod_ = @Autowired)
    private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(ctx).build();
    }

/*	@Test
	public void testListPaging() throws Exception {
		log.info(mockMvc.perform(
				MockMvcRequestBuilders.get("/board/list")
				.param("pageNum", "2")
				.param("amount", "5")).andReturn().getModelAndView().getModelMap());
	}*/
	
/*	@Test
	public void testRemove() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/remove")
				.param("bno", "11"))
				.andReturn().getModelAndView().getViewName();
		log.info(resultPage);
	}*/
	
	
/*	@Test
	public void testModify() throws Exception {
		String resultPage = mockMvc.perform(MockMvcRequestBuilders.post("/board/modify")
				.param("bno", "7")
				.param("title", "������ �׽�Ʈ ���� ����")
				.param("content", "������ �׽�Ʈ ���� ����")
				.param("writer", "user00"))
				.andReturn().getModelAndView().getViewName();
		
		log.info(resultPage);
	}*/

    @Test
    public void testGet() throws Exception {
        log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/get").param(
                "bno", "6")).andReturn().getModelAndView().getModelMap());
    }
    /*
     * @Test public void testList() throws Exception {
     * log.info(mockMvc.perform(MockMvcRequestBuilders.get("/board/list")).andReturn
     * ().getModelAndView().getModelMap()); }
     */

    /*
     * @Test public void testRegister() throws Exception { String resultPage =
     * mockMvc.perform(MockMvcRequestBuilders.post("/board/register").param("title",
     * "�׽�Ʈ ���� ����1") .param("content", "�׽�Ʈ ���� ����1").param("writer",
     * "�׽�Ʈ �۾���1")).andReturn().getModelAndView().getViewName();
     * log.info(resultPage); }
     */
}
