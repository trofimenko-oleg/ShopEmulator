package com.myshop.service.to;

import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class OrderForm {
    private List<ShortenedOrderItem> orderItems;

    public List<ShortenedOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<ShortenedOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
