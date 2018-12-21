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

//    @Setter(onMethod_={@Autowired})
    ZptutxptcService zptutxptcService;


    @PostMapping(value = "/zptutxptc", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ZptutxptcOutputVO> zptutcptc(@RequestHeader("CustomizeHeader") String Header,
                                                       @RequestBody ZptutxptcInputVO inputVO) {

        return zptutxptcService.syncCall(Header, inputVO);
    }

    @GetMapping(value = "/")
    public String home(Model model) {
        model.addAttribute("hi", "Hello~~");
        return "home";
    }
}
