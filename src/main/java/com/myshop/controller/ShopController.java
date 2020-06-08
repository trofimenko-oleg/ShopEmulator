package com.myshop.controller;

import com.myshop.domain.Drink;
import com.myshop.service.DrinkService;
import com.myshop.service.OrderService;
import com.myshop.service.ShortenedOrderItemService;
import com.myshop.service.to.ShortenedOrderItem;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
public class ShopController {
    private static final Logger log = getLogger(ShopController.class);

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private ShortenedOrderItemService shortenedOrderItemService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public String root()
    {
        return "index";
    }

//    @GetMapping("/shop")
//    public String drinkList(ModelMap model) {
//        List<ShortenedOrderItem> shortenedOrderItemList = new ArrayList<>();
//        for (Drink drink: drinkService.getAll())
//        {
//            shortenedOrderItemList.add(new ShortenedOrderItem(drink, 0));
//        }
//        model.addAttribute("items", shortenedOrderItemList);
//        //return shortenedOrderItemList;
//        return "shop";
//    }

    @GetMapping("/carto")
    List<ShortenedOrderItem> drinkListPost(ModelMap model) {
        List<ShortenedOrderItem> shortenedOrderItemList = new ArrayList<>();
        for (Drink drink: drinkService.getAll())
        {
            shortenedOrderItemList.add(new ShortenedOrderItem(drink, 0));
        }
        //model.addAttribute("items", shortenedOrderItemList);
        return shortenedOrderItemList;
    }

    @GetMapping("/shop")
    public String drinkList(Model model) {
        List<ShortenedOrderItem> shortenedOrderItemList = new ArrayList<>();
        for (Drink drink: drinkService.getAll())
        {
            shortenedOrderItemList.add(new ShortenedOrderItem(drink, 0));
        }
        model.addAttribute("items", shortenedOrderItemList);
        return "shop";
    }

    @GetMapping("/cart")
    public String showCart(ModelMap model) {
        model.addAttribute("order", shortenedOrderItemService.getItems(orderService.get(2)));
        return "cart";
    }

    @PostMapping(value = "/shop/cart", consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
    public String showCart(@RequestBody List<ShortenedOrderItem> list, ModelMap model) {
        //model.addAttribute("drinks", drinkService.getAll());
        model.addAttribute("order", list);
//        model.addAttribute("order", shortenedOrderItemService.getItems(orderService.get(2)));
        return "cart";
    }
}
