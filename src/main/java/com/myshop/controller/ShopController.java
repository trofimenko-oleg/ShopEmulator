package com.myshop.controller;

import com.myshop.domain.Drink;
import com.myshop.domain.Order;
import com.myshop.service.DrinkService;
import com.myshop.service.OrderService;
import com.myshop.service.ShortenedOrderItemService;
import com.myshop.service.to.OrderForm;
import com.myshop.service.to.ShortenedOrderItem;
import com.sun.org.apache.xpath.internal.operations.Mod;
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

    private OrderForm getOrderedItemsOrReturnBackIfNoItems(OrderForm orderForm)
    {
        List<ShortenedOrderItem> items = orderForm.getOrderItems();
        List<ShortenedOrderItem> toCart = new ArrayList<>();
        OrderForm newOrderForm = new OrderForm();
        for (ShortenedOrderItem item: items)    {
            if (item.getQuantity() > 0) {
                toCart.add(item);
            }
        }
        if (toCart.size() > 0) {
            newOrderForm.setOrderItems(toCart);
        }
        return newOrderForm;
    }

    @PostMapping(value = "/saveOrder")
    public ModelAndView saveOrder(@ModelAttribute("order") OrderForm orderForm) {
            OrderForm newOrderForm = getOrderedItemsOrReturnBackIfNoItems(orderForm);
            ModelAndView modelAndView = new ModelAndView();

        if (newOrderForm.getOrderItems().size() == 0) {
            modelAndView.setViewName("shop");
        }
        else  {
            Order order = orderService.save(shortenedOrderItemService.getOrderFromItemList(newOrderForm.getOrderItems()));
            //modelAndView.setViewName("orderadditionalinfo");
            modelAndView.addObject("id", order.getId());
            modelAndView.setViewName("orderadditionalinfo?id="+order.getId());
        }
        return modelAndView;
    }

    @PostMapping(value="/save_additional_order_info")
    public String saveAdditionalInfo(@RequestParam("id") int id, Model model)
    {
        Order order = orderService.get(id);
        if (model.getAttribute("additional_info") != null)
        {
            String info = (String)model.getAttribute("additional_info");
            order.setShippingInfo(info);
            orderService.save(order);
        }
        return "shop";
    }
}
