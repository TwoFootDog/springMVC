package com.test.controller;

import com.test.service.serviceInterface.BoardService;

import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class HomeController {

    @Setter(onMethod_ = {@Autowired})
    private BoardService boardService;

    @GetMapping("/{bno}")
    public String home(Model model, @PathVariable int bno) {
        String replyer = boardService.selectReplyer(bno);
        model.addAttribute("hi", replyer);
        return "home";
    }
}