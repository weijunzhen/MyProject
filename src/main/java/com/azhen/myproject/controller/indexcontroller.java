package com.azhen.myproject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class indexcontroller {
    @GetMapping("/")
    public String index(){
        return "index";
    }
}
