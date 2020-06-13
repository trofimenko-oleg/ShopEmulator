package com.myshop.service;

import com.myshop.domain.Drink;
import com.myshop.service.to.OrderForm;
import com.myshop.service.to.ShortenedOrderItem;
import com.myshop.util.exception.NotEnoughProductInStorage;

import javax.transaction.Transactional;
import java.util.List;


public interface PurchaseService {
    void buy(Drink drink, int quantity) throws NotEnoughProductInStorage;
    void buy(OrderForm orderForm) throws NotEnoughProductInStorage;
    void buy(List<ShortenedOrderItem> shortenedOrderItemList) throws NotEnoughProductInStorage;
}
