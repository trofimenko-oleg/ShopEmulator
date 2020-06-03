package com.myshop.controller;

import com.myshop.domain.Drink;
import com.myshop.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ShopController {

    @Autowired
    private DrinkService drinkService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/list")
    public String drinkList(Model model) {
        model.addAttribute("drinks", drinkService.getAll());
        for (Drink drink: drinkService.getAll())
        {
            System.out.println(drink);
        }
        return "shop";
    }

    @GetMapping("/hello")
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello!");
        return "hello";
    }



}
