package org.zerock.contoller;





import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.zerock.domain.SampleVO;

import lombok.extern.log4j.Log4j;

@RestController
@RequestMapping("/sample")
@Log4j
public class SampleController {
	
	@GetMapping(value = "/check" , params = {"height", "weight"})
	public ResponseEntity<SampleVO> check(Double height, Double weight) {
		SampleVO vo = new SampleVO(0, ""+ height, "" + weight );
		ResponseEntity<SampleVO> result = null;
		
		if(height < 150 ) { 
			result = ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(vo);
		} else {
			result = ResponseEntity.status(HttpStatus.OK).body(vo);
		}
		
		return result;
	}
	
	@GetMapping(value="/getMap")
	public Map<String, SampleVO> getMap() {
		Map<String, SampleVO> map = new HashMap<>();
		map.put("First", new SampleVO(11, "22", "33"));
		
		return map;
		
	}
	
	@GetMapping(value = "/getList")
	public List<SampleVO> getList() {
		return IntStream.rangeClosed(1,10).mapToObj(i -> new SampleVO(i, i + "First", i + "Last")).collect(Collectors.toList());
	}
	
//	@GetMapping(value="/getSample", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE, MediaType.APPLICATION_XML_VALUE})
	@GetMapping(value="/getSample")
	public SampleVO getSample() {
		
		return new SampleVO(112, "스타", "로드");
	}

	@GetMapping(value = "/getText", produces = "text/plain; charset=UTF-8")
	public String getText() {
		log.info("MIME TYPE : " + MediaType.TEXT_PLAIN_VALUE);
		
		return "안녕하세요";
	}
}
