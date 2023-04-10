package com.example.board.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class MainController {

    @GetMapping("/jsp")
    public String jspRoot(){
        System.out.println("jsp call");

        return "forward:/articles/jsp";
    }

    @GetMapping
    public String thymeleafRoot(){

        System.out.println("thymeleaf call");

        return "forward:/articles/th";
    }
}
