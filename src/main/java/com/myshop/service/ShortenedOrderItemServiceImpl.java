package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.domain.Order;
import com.myshop.domain.OrderDetails;
import com.myshop.service.to.OrderClientView;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.util.PriceUtil;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.myshop.util.FormattersUtil.round;
@Service
public class ShortenedOrderItemServiceImpl implements ShortenedOrderItemService{
    @Override
    public List<ShortenedOrderItem> getItems(Order order) {
        List<ShortenedOrderItem> list = new ArrayList<>();
        for (OrderDetails item : order.getOrders()) {
            list.add(new ShortenedOrderItem(item.getDrink(), item.getDrinkQuantity()));
        }
        return list;
    }

    @Override
    public Order getOrderFromItemList(List<ShortenedOrderItem> items) {
        Order order = new Order();
        double totalSum = 0;
        List<OrderDetails> orderDetails = new ArrayList<>();
         for (ShortenedOrderItem item: items)
         {
             double itemPrice = item.getPriceWithoutDiscount();
             int quantity = item.getQuantity();
             orderDetails.add(new OrderDetails(order, item.getDrink(), quantity, itemPrice));
             totalSum += itemPrice*quantity;
         }
         order.setOrders(orderDetails);
         order.setTotalCheckValue(totalSum);
         order.setTime(LocalTime.now());
         order.setLocalDate(LocalDate.now());
         order.setDayOfWeek(LocalDate.now().getDayOfWeek());
         return order;
    }
}
