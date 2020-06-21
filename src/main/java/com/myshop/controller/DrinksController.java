package com.myshop.controller;

import com.myshop.domain.*;
import com.myshop.service.DrinkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
        if (drink.getClass().equals(AlcoholicDrink.class)){
            AlcoholicDrink alcoholicDrink = (AlcoholicDrink) drink;
            model.addAttribute("drink", alcoholicDrink);
            model.addAttribute("groupValues", AlcoholicGroup.values());
        }
        else if (drink.getClass().equals(NonAlcoholicDrink.class)){
            NonAlcoholicDrink nonAlcoholicDrink = (NonAlcoholicDrink)drink;
            model.addAttribute("drink", nonAlcoholicDrink);
            model.addAttribute("groupValues", NonAlcoholicGroup.values());
        }
        //model.addAttribute("drink", drink);
        return "editdrink";
    }

    @GetMapping(value = "/drink/remove/{id}")
    public String removeDrink(Model model, @PathVariable int id){
        Drink drink = drinkService.get(id);
        drinkService.delete(id);
        return drinkList(model);
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

    @GetMapping(value = "/drink/alcoholic")
    public String getAlcoholic(Model model){
        List<AlcoholicDrink> alcoholicDrinks = new ArrayList<>();
        for (Drink drink: drinkService.getAll()){
            if (drink.getClass().equals(AlcoholicDrink.class)){
                AlcoholicDrink alcoholicDrink = (AlcoholicDrink) drink;
                alcoholicDrinks.add(alcoholicDrink);
            }
        }
        model.addAttribute("drinksList", alcoholicDrinks);
        return "drinkList";
    }

    @GetMapping(value = "/drink/nonalcoholic")
    public String getNonAlcoholic(Model model){
        List<NonAlcoholicDrink> nonalcoholicDrinks = new ArrayList<>();
        for (Drink drink: drinkService.getAll()){
            if (drink.getClass().equals(NonAlcoholicDrink.class)){
                NonAlcoholicDrink nonalcoholicDrink = (NonAlcoholicDrink) drink;
                nonalcoholicDrinks.add(nonalcoholicDrink);
            }
        }
        model.addAttribute("drinksList", nonalcoholicDrinks);
        return "drinkList";
    }

    @GetMapping(value = "/drink/search")
    public String search(Model model, @RequestParam("search") String searchText){
        String search = searchText;
        List<Drink> list = drinkService.getByPartOfName(searchText);
        model.addAttribute("drinksList", list);
        return "drinkList";
    }
}
