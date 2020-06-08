package com.myshop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "index";
    }

//    @GetMapping("/shop")
//    public String shop(Model model) {
//        return "shop";
//    }
//
//    @GetMapping("/cart")
//    public String cart() {
//        return "cart";
//    }

}
