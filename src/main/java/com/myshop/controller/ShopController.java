package com.myshop.controller;

import com.myshop.service.DrinkService;
import com.myshop.service.OrderService;
import com.myshop.service.ShortenedOrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ShopController {

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private ShortenedOrderItemService shortenedOrderItemService;

    @Autowired
    private OrderService orderService;

    @GetMapping("/")
    public String root() {
        return "index";
    }

    @GetMapping("/shop")
    public String drinkList(Model model) {
        model.addAttribute("drinks", drinkService.getAll());
        return "shop";
    }

//    @GetMapping("/cart")
//    public String showCart(ModelMap model) {
//        model.addAttribute("drinks", drinkService.getAll());
//        return "cart";
//    }

    @PostMapping("/cart")
    public String showCart(ModelMap model) {
        //model.addAttribute("drinks", drinkService.getAll());
        model.addAttribute("order", shortenedOrderItemService.getItems(orderService.get(2)));
        return "cart";
    }
}
