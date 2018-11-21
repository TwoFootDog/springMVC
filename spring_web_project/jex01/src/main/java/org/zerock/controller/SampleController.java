package org.zerock.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.zerock.domain.SampleDTO;
import org.zerock.domain.SampleDTOList;
import org.zerock.domain.TodoDTO;

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
	
	@GetMapping("/jex02List")
	public String jex02List(@RequestParam("ids") ArrayList<String> ids) {
		log.info("ids : " + ids);
		return "jex02List";
	}
	
	@GetMapping("/jex02Array")
	public String jex02Array(@RequestParam("ids") String[] ids) {
		log.info("ids : " + Arrays.toString(ids));
		return "jex02Array";
	}
	
	@GetMapping("/jex02Bean")
	public String jex02Bean(SampleDTOList list) {
		log.info("list dtos : " + list);
		return "jex02Bean";
	}
	
/*	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
		binder.registerCustomEditor(java.util.Date.class, new CustomDateEditor(dateFormat, false));
	}*/
	
	@GetMapping("/jex03")
	public String jex03(TodoDTO todo) {
		log.info("todo : " + todo);
		return "jex03";
	}
	
}
