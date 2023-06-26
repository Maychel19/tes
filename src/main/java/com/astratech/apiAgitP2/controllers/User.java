package com.astratech.apiAgitP2.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class User {
    @GetMapping("/")
    public String index() {
        return "index";
    }
}
