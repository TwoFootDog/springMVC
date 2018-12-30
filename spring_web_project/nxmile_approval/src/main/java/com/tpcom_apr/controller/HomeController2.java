package com.tpcom_apr.controller;

import com.tpcom_apr.domain.OnmsgchkInputVO;
import com.tpcom_apr.domain.OnmsgchkOutputVO;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
public class HomeController2 {

    @PostMapping(value = "/home")
    public OnmsgchkOutputVO home2(@RequestBody OnmsgchkInputVO inputVO, Model model) {
        model.addAttribute("hi222222", "Hello~~22222");
        return new OnmsgchkOutputVO();
    }
}
