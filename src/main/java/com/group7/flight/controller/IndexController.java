package com.group7.flight.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Slf4j
@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        System.out.println("IndexController method triggered");
        return "index";
    }

    @GetMapping("/index")
    public String indexPage() {
        System.out.println("Index page method triggered");
        return "index";
    }
}
