package com.myshop.controller;

import com.myshop.domain.AlcoholicDrink;
import com.myshop.domain.Drink;
import com.myshop.domain.NonAlcoholicDrink;
import com.myshop.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class DrinksController {

    @Autowired
    DrinkService drinkService;

    @GetMapping(value = "/drink/list")
     public String drinkList(Model model){
        model.addAttribute("drinksList", drinkService.getAll());
        return "drinkList";
    }

    @GetMapping(value = "/drink/edit/{id}")
    public String editDrink(Model model, @PathVariable int id){
        Drink drink = drinkService.get(id);
//        if (drink.getClass().equals(AlcoholicDrink.class)){
//            model.addAttribute("drink", (AlcoholicDrink)drink);
//        }
//        else if (drink.getClass().equals(NonAlcoholicDrink.class)){
//            model.addAttribute("drink", (NonAlcoholicDrink)drink);
//        }
        model.addAttribute("drink", drink);
        return "editdrink";

    }

    @PostMapping(value = "/drink/saveAlcoholic")
    public String saveAlco(@ModelAttribute ("drink") AlcoholicDrink drink, Model model){
        AlcoholicDrink test = drink;
        drinkService.save(drink);

        return drinkList(model);
    }

    @PostMapping(value = "/drink/saveNonAlcoholic")
    public String saveNonAlco(@ModelAttribute ("drink") NonAlcoholicDrink drink, Model model){
        NonAlcoholicDrink test = drink;
        drinkService.save(drink);
        return drinkList(model);

        //return "drinkList";
    }
}
