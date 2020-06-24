package com.myshop.service;

import com.myshop.domain.Order;
import com.myshop.service.to.ShortenedOrderItem;
import java.util.List;

public interface ShortenedOrderItemService {
    List<ShortenedOrderItem> getItems(Order order);
    Order getOrderFromItemList(List<ShortenedOrderItem> items);
}
