package com.myshop.domain;

import javax.persistence.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
@NamedQueries({
        @NamedQuery(name = Order.BY_DATE, query = "SELECT o FROM Order o ORDER BY o.localDate"),
        @NamedQuery(name = Order.BY_TIME, query = "SELECT o FROM Order o ORDER BY o.time")

})
@Table(name="orders")
@Entity
public class Order extends AbstractBaseEntity {
    public static final String BY_DATE = "Order.byDate";
    public static final String BY_TIME = "Order.byTime";


    //TODO: rename orders to items
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<OrderDetails> orders;
    @Column
    @Enumerated
    private DayOfWeek dayOfWeek;
    @Column
    private LocalTime time;
    @Column(name="date")
    private LocalDate localDate;
    @Column(name="check_value")
    private double totalCheckValue;
    @Column(name="shipping_info")
    private String shippingInfo;

    public Order() {
    }

    public Order(Order other)
    {
        orders = new ArrayList<>();
        for (OrderDetails orderDetails: other.orders)
        {
            orders.add(new OrderDetails(this, orderDetails));
        }
        this.dayOfWeek = other.dayOfWeek;
        this.time = other.time;
        this.localDate = other.localDate;
        this.totalCheckValue = other.totalCheckValue;
        this.shippingInfo = other.shippingInfo;
    }

    public Order(List<OrderDetails> orders, DayOfWeek dayOfWeek, LocalTime time, LocalDate localDate) {
        this.orders = orders;
        this.dayOfWeek = dayOfWeek;
        this.time = time;
        this.localDate = localDate;
    }

    public List<OrderDetails> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderDetails> orders) {
        this.orders = orders;
    }

    public DayOfWeek getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(DayOfWeek dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public LocalTime getTime() {
        return time;
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public double getTotalCheckValue() {
        return totalCheckValue;
    }

    public void setTotalCheckValue(double totalCheckValue) {
        this.totalCheckValue = totalCheckValue;
    }

    public String getShippingInfo() {
        return shippingInfo;
    }

    public void setShippingInfo(String shippingInfo) {
        this.shippingInfo = shippingInfo;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orders=" + orders +
                '}';
    }
}
