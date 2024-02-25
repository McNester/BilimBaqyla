package com.oop.bilimbaqyla.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("title","HELLO FROM SERAFIM");
        return "index";
    }
}
