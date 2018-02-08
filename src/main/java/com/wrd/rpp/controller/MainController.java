package com.wrd.rpp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @RequestMapping(value = {"loginUI"})
    public String loginUI(){
        return "login";
    }

    @RequestMapping(value = {"index"})
    public String index(){
        return "index";
    }
}
