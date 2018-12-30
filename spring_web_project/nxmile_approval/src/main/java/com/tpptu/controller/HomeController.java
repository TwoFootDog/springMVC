package com.tpptu.controller;


import com.tpptu.domain.ZptutxptcInputVO;
import com.tpptu.domain.ZptutxptcOutputVO;
import com.tpptu.service.ZptutxptcService;
import io.swagger.annotations.*;


import lombok.Setter;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
//@AllArgsConstructor
@Log4j
@Api(value = "${HomeController.value}", description = "${HomeController.description}") // Controller에 대한 Swagger 설명
public class HomeController {

    // json injection
    // 공통 값들은 json이나 xml이나 리소스로 관리하여 불러옴
    // requestheader 어노테이션 수정(토비 유튜브 react 1편, iterator)
    //junit assert
    // front controller pattern
    // swagger

    @Setter(onMethod_ = {@Autowired})
    ZptutxptcService zptutxptcService;

    @Value("${HomeController.zptutxptc.notes}")
    private String notes;

    @ApiOperation(  // API에 대한 Swagger 설명
            value="서비스",
            notes = "서비스입니다.",
            httpMethod = "POST",
            consumes = "application/json",
            produces = "application/json",
            protocols = "http",
            responseHeaders = {
                    //headers
            })
    @ApiResponses({ // Response Message에 대한 Swagger 설명
            @ApiResponse(code = 200, message = "OK"),
            @ApiResponse(code = 404, message = "No params")
    })
    @PostMapping(value="/zptutxptc", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZptutxptcOutputVO> zptutcptc(HttpServletRequest Header,
                                                       @RequestBody ZptutxptcInputVO inputVO) {
        return zptutxptcService.syncCall(Header, inputVO);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "area", value = "지역", required = true, dataType = "String", paramType = "path"),
            @ApiImplicitParam(name = "param1", value = "파라미터1", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "param2", value = "파마미터2", required = false, dataType = "int", paramType = "query")
    })
    @GetMapping(value = "/home/{area}")
    public String home(@PathVariable String area, @RequestParam String param1, @RequestParam int param2) {
        return "home";
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("hi", "Hello~~");
        return "home";
    }
}
