package com.myshop.service.to;

import java.util.List;

public class OrderForm {
    private List<ShortenedOrderItem> orderItems;

    public List<ShortenedOrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<ShortenedOrderItem> orderItems) {
        this.orderItems = orderItems;
    }
}
