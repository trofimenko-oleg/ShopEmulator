package com.myshop.controller;

import com.myshop.domain.Drink;
import com.myshop.service.DrinkService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/hello")
public class ShopController {

    private final DrinkService drinkService;

    public ShopController(DrinkService drinkService) {
        this.drinkService = drinkService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String printWelcome(ModelMap model) {
        model.addAttribute("message", "Hello!");
        return "hello.jsp";
    }

    List<Drink> getAll(){
        return drinkService.getAll();
    }


}
