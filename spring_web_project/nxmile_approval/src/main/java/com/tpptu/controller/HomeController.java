package com.tpptu.controller;

import com.commons.domain.CustomizeHeaderVO;
import com.tpptu.domain.ZptutxptcInputVO;
import com.tpptu.domain.ZptutxptcOutputVO;
import com.tpptu.service.ZptutxptcService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HomeController {

    // json injection
    // 공통 값들은 json이나 xml이나 리소스로 관리하여 불러옴
    // requestheader 어노테이션 수정(토비 유튜브 react 1편, iterator)
    //junit assert
    // front controller pattern
    // swagger

//    @Setter(onMethod_={@Autowired})
    ZptutxptcService zptutxptcService;


    @PostMapping(value = "/zptutxptc", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZptutxptcOutputVO> zptutcptc(@RequestHeader CustomizeHeaderVO Header,
                                                       @RequestBody ZptutxptcInputVO inputVO) {

        return zptutxptcService.syncCall(Header, inputVO);
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("hi", "Hello~~");
        return "home";
    }
}
