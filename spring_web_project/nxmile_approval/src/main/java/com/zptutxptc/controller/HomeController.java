package com.zptutxptc.controller;

import com.zptutxptc.domain.ZptutxptcInputVO;
import com.zptutxptc.domain.ZptutxptcOutputVO;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

    @PostMapping("/zptutxptc")
    public ZptutxptcOutputVO zptutcptc(@RequestBody ZptutxptcInputVO inputVO) {

        return new ZptutxptcOutputVO();
    }

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("hi", "Hello~~");
        return "home";
    }
}
