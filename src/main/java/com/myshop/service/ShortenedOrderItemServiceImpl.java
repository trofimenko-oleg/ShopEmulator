package com.myshop.service;

import com.myshop.domain.Order;
import com.myshop.domain.OrderDetails;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.util.TimeUtil;
import org.springframework.stereotype.Service;
import java.time.*;
import java.util.ArrayList;
import java.util.List;
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
         for (ShortenedOrderItem item: items){
             double itemPrice = item.getAverageItemPrice();
             int quantity = item.getQuantity();
             orderDetails.add(new OrderDetails(order, item.getDrink(), quantity, itemPrice));
             totalSum += itemPrice*quantity;
         }
        LocalDateTime localDateTime = LocalDateTime.ofInstant(TimeUtil.getClock().instant(), TimeUtil.getDefaultZoneId());
        order.setOrders(orderDetails);
        order.setTotalCheckValue(round(totalSum));
        order.setTime(localDateTime.toLocalTime());
        order.setLocalDate(localDateTime.toLocalDate());
        order.setDayOfWeek(localDateTime.getDayOfWeek());
        return order;
    }
}
