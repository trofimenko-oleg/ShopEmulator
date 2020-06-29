package com.myshop.domain;

import javax.persistence.*;
import java.util.Objects;

@Table(name = "ordersdetails")
@Entity
public class OrderDetails extends AbstractBaseEntity {

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @ManyToOne
    @JoinColumn(name = "drink_id", nullable = false)
    private Drink drink;

    @Column(name = "drink_quantity")
    private int drinkQuantity;

    @Column(name = "drink_price")
    private double itemPrice;

    public OrderDetails() {
    }

    public OrderDetails(Order order, OrderDetails other) {
        this(order, other.drink, other.drinkQuantity, other.itemPrice);
    }

    public OrderDetails(Order order, Drink drink, int drinkQuantity, double itemPrice) {
        this.order = order;
        this.drink = drink;
        this.drinkQuantity = drinkQuantity;
        this.itemPrice = itemPrice;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getDrinkQuantity() {
        return drinkQuantity;
    }

    public void setDrinkQuantity(int drinkQuantity) {
        this.drinkQuantity = drinkQuantity;
    }

    public double getCostWithoutMarkup() {
        return drinkQuantity * drink.getPurchasePrice();
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "drink=" + drink +
                ", drinkQuantity=" + drinkQuantity +
                ", itemPrice=" + itemPrice +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderDetails)) return false;
        OrderDetails that = (OrderDetails) o;
        return getDrinkQuantity() == that.getDrinkQuantity() &&
                getOrder().equals(that.getOrder()) &&
                getDrink().equals(that.getDrink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getDrink(), getDrinkQuantity());
    }
}
