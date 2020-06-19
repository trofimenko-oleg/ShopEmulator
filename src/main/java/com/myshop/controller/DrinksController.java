package com.myshop.controller;

import com.myshop.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DrinksController {

    @Autowired
    DrinkService drinkService;

    @GetMapping(value = "/drink/list")
     public String drinkList(Model model){
        model.addAttribute("drinksList", drinkService.getAll());
        return "drinkList";

    }
}
