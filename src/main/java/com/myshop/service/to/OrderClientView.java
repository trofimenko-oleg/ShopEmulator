package com.myshop.service.to;

import com.myshop.domain.Drink;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

public class OrderClientView {

    private Map<Drink, DrinkDetails> order = new HashMap<>();
    private String shippingInfo;

    public class DrinkDetails
    {
        private Double price;
        private Integer quantity;

        public DrinkDetails() {
        }

        public Double getPrice() {
            return price;
        }

        public void setPrice(Double price) {
            this.price = price;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public DrinkDetails(Double price, Integer quantity) {
            this.price = price;
            this.quantity = quantity;
        }
    }

    public OrderClientView() {
    }

    public OrderClientView(Map<Drink, DrinkDetails> order) {
        this.order = order;
    }

    public Map<Drink, DrinkDetails> getOrder() {
        return order;
    }

    public void addToOrderMap(Drink drink, Double price, Integer quantity)
    {
        addToOrderMap(drink, new DrinkDetails(price, quantity));
    }

    public void addToOrderMap(Drink drink, DrinkDetails drinkDetails)
    {
        order.put(drink, drinkDetails);
    }

    public void setOrder(Map<Drink, DrinkDetails> order) {
        this.order = order;
    }

    public String getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(String shippingInfo) {
        this.shippingInfo = shippingInfo;
    }
}
