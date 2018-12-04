package org.zerock.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zerock.service.SampleService;

import lombok.Setter;
import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@Log4j
@ContextConfiguration({"file:src/main/webapp/WEB-INF/spring/root-context.xml"})
public class SampleServiceTests {

	@Setter(onMethod_= @Autowired)
	private SampleService service;
	
	@Test
	public void testClass() throws Exception {
		log.info(service);
		log.info(service.getClass().getName());
//		log.info(service.doAdd("123", "456"));
		log.info(service.doAdd("123", "456"));
	}
	
}
