package com.myshop.controller;

import com.myshop.domain.Drink;
import com.myshop.domain.Order;
import com.myshop.service.*;
import com.myshop.service.to.OrderForm;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.simulator.Shop;
import com.myshop.simulator.Simulator;
import com.myshop.util.exception.NotEnoughProductInStorage;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private RefillingService refillingService;


    @GetMapping("/")
    public ModelAndView root() throws NotEnoughProductInStorage {
        Simulator.startSimulator(30);
        return new ModelAndView("redirect:/shop");
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
    public ModelAndView showCart(@ModelAttribute("order") OrderForm orderForm, HttpServletRequest request) {
        List<ShortenedOrderItem> items = orderForm.getOrderItems();
        //to prevent wrong refresh, after refreshing page orderForm is downloading in not expected (for me) way
        for (ShortenedOrderItem item: items)
        {
            if (item.getDrink() == null){
                //return drinkList();
                return new ModelAndView("redirect:shop");
            }
        }
        List<ShortenedOrderItem> toCart = new ArrayList<>();
        OrderForm returned = new OrderForm();
        for (ShortenedOrderItem item: items)    {
            if (item.getQuantity() > 0) {
                toCart.add(item);
            }
        }
        if (toCart.size() == 0) {
            //return new ModelAndView("shop", "order", orderForm);
            return drinkList();
        }
         else  {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("cart");
            returned.setOrderItems(toCart);
            modelAndView.addObject("order", returned);
            return modelAndView;
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
    public ModelAndView saveOrder(@ModelAttribute("order") OrderForm orderForm, HttpServletRequest request, @ModelAttribute String id, ModelAndView mav) throws NotEnoughProductInStorage {
            String id1 = id;
            if ((request.getSession().getAttribute("id")) != null)
            {
                return new ModelAndView("orderadditionalinfo");
            }
            OrderForm newOrderForm = getOrderedItemsOrReturnBackIfNoItems(orderForm);
            ModelAndView modelAndView = new ModelAndView();

        if (newOrderForm.getOrderItems().size() == 0) {
            modelAndView.setViewName("shop");
        }
        else  {
               Order order = orderService.save(shortenedOrderItemService.getOrderFromItemList(newOrderForm.getOrderItems()));
              // request.getSession().invalidate();
               request.getSession().setAttribute("id", order.getId());
               mav.addObject("id", order.getId());
            modelAndView.addObject("id", order.getId());
            modelAndView.setViewName("orderadditionalinfo");
        }
        return modelAndView;
    }

    @PostMapping(value="/save_additional_order_info/{id}")
    public ModelAndView saveAdditionalInfo(@PathVariable int id, @ModelAttribute("info") String info, HttpServletRequest request, RedirectAttributes ra) throws NotEnoughProductInStorage {
        Order order = orderService.get(id);
        if (info != null)
        {
            order.setShippingInfo(info);
            orderService.save(order);
        }
        else {
            //throw new Exception
        }
        ModelAndView modelAndView = new ModelAndView("redirect:/shop");
        request.getSession().invalidate();

        //RedirectView redirectView = new RedirectView("shop");
        //redirectView.setExposeModelAttributes(false);
        //modelAndView.setView(redirectView);
        return modelAndView;
        //return new ModelAndView("redirect:/shop");

    }
}
