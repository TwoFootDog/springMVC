package org.zerock.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;

import lombok.extern.log4j.Log4j;

@Controller
@RequestMapping("/sample/*")
@Log4j
public class SampleController {

	@RequestMapping("")
	public void basic() {
		log.info("basic......................");
	}

	@RequestMapping(value = "/basic", method = { RequestMethod.GET, RequestMethod.POST })
	public void basicGet() {
		log.info("basic get..........");
	}
	
	@GetMapping("/basicOnlyGet")
	public void basicGet2() {
		log.info("basic get only get...........");
	}
	
	@GetMapping("/jex01")
	public String jex01(SampleDTO dto) {
		log.info(" " + dto);
		return "jex01";
	}
	
	@GetMapping("/jex02")  
	public String jex02(@RequestParam("name") String name, @RequestParam("age") int age) {
		
		log.info("name : " + name);
		log.info("age : " + age);
		return "jex02";
	}
	
}
