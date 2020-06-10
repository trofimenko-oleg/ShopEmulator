package com.myshop.controller;

import com.myshop.domain.Drink;
import com.myshop.domain.Order;
import com.myshop.service.DrinkService;
import com.myshop.service.OrderService;
import com.myshop.service.ShortenedOrderItemService;
import com.myshop.service.to.OrderForm;
import com.myshop.service.to.ShortenedOrderItem;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

@Controller
@SessionAttributes(value = "order")
public class ShopController {
    private static final Logger log = getLogger(ShopController.class);

    @Autowired
    private DrinkService drinkService;

    @Autowired
    private ShortenedOrderItemService shortenedOrderItemService;

    @Autowired
    private OrderService orderService;


    @GetMapping("/")
    public ModelAndView root()
    {
        return new ModelAndView("index");
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

//    @GetMapping("/carto")
//    List<ShortenedOrderItem> drinkListPost(ModelMap model) {
//        List<ShortenedOrderItem> shortenedOrderItemList = new ArrayList<>();
//        for (Drink drink: drinkService.getAll())
//        {
//            shortenedOrderItemList.add(new ShortenedOrderItem(drink, 0));
//        }
//        //model.addAttribute("items", shortenedOrderItemList);
//        return shortenedOrderItemList;
//    }

//    @GetMapping("/shop")
//    public String drinkList(Model model) {
//        List<ShortenedOrderItem> shortenedOrderItemList = new ArrayList<>();
//        for (Drink drink: drinkService.getAll())
//        {
//            shortenedOrderItemList.add(new ShortenedOrderItem(drink, 0));
//        }
//        model.addAttribute("items", shortenedOrderItemList);
//        return "shop";
//    }
    @GetMapping("/shop")
    public ModelAndView drinkList() {
        List<ShortenedOrderItem> shortenedOrderItemList = new ArrayList<>();
        ModelAndView modelAndView = new ModelAndView();
        for (Drink drink: drinkService.getAll())
        {
            shortenedOrderItemList.add(new ShortenedOrderItem(drink, 0));
        }
        OrderForm orderForm = new OrderForm();
        orderForm.setOrderItems(shortenedOrderItemList);
        modelAndView.addObject("order", orderForm);
        modelAndView.setViewName("shop");
        return modelAndView;
    }

//    @GetMapping("/cart")
//    public ModelAndView showCart2(@ModelAttribute("order") OrderForm orderForm) {
//        OrderForm returned = new OrderForm();
//        returned.setOrderItems(shortenedOrderItemService.getItems(orderService.get(2)));
//        return new ModelAndView("cart", "order", returned);
//    }

    @PostMapping(value = "/cart")
    public ModelAndView showCart(@ModelAttribute("order") OrderForm orderForm) {
        List<ShortenedOrderItem> items = orderForm.getOrderItems();
        List<ShortenedOrderItem> toCart = new ArrayList<>();
        OrderForm returned = new OrderForm();
        for (ShortenedOrderItem item: items)    {
            if (item.getQuantity() > 0) {
                toCart.add(item);
            }
        }
        if (toCart.size() == 0) {
            return new ModelAndView("shop", "order", orderForm);
        }
         else  {
             orderForm.setOrderItems(toCart);
             return new ModelAndView("cart", "order", orderForm);
        }
    }

//
//    @PostMapping(value = "/cart", consumes = "application/x-www-form-urlencoded")
//    public ModelAndView showCart2(List<ShortenedOrderItem> list) {
//        List<ShortenedOrderItem> items = new ArrayList<>();
//        for (ShortenedOrderItem item: list)    {
//            if (item.getQuantity() > 0) {
//                items.add(item);
//            }
//        }
//        if (items.size() == 0) {
//            return new ModelAndView("shop", "items", list);
//        }
//        else  {
//            return new ModelAndView("cart", "order", items);
//        }
//    }

//    @PostMapping(value = "/cart", consumes = "application/json")
//    public ModelAndView showCart(@RequestBody List<ShortenedOrderItem> list) {
//        ModelAndView mav = new ModelAndView();
//        List<ShortenedOrderItem> items = new ArrayList<>();
//        for (ShortenedOrderItem item: list)    {
//            if (item.getQuantity() > 0) {
//                items.add(item);
//            }
//        }
//
//        if (items.size() == 0) {
//            //return new ModelAndView("shop", "items", list);
//            mav.setViewName("shop");
//            mav.addObject("items", list);
//        }
//        else  {
//            mav.setViewName("cart");
//            mav.addObject("order", items);
//            //return new ModelAndView("cart", "order", items);
//        }
//        return mav;
//    }

//    @PostMapping(value = "/shop/cart", consumes = MediaType.APPLICATION_JSON_VALUE, headers = "Accept=application/json")
//    public String showCart(@RequestBody List<ShortenedOrderItem> list, ModelMap model) {
//        //model.addAttribute("drinks", drinkService.getAll());
//        model.addAttribute("order", list);
////        model.addAttribute("order", shortenedOrderItemService.getItems(orderService.get(2)));
//        return "cart";
//    }
}
