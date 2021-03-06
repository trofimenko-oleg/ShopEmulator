package com.myshop.service.to;

import com.myshop.domain.Drink;
import com.myshop.util.PriceUtil;
import org.springframework.stereotype.Component;

import java.util.Objects;

import static com.myshop.util.FormattersUtil.round;

@Component
public class ShortenedOrderItem {
    private Drink drink;
    private int quantity;
    private double priceWithoutDiscount;
    private double priceWithDiscount;
    private double averageItemPrice;

    public ShortenedOrderItem() {
    }

    public ShortenedOrderItem(Drink drink, int quantity) {
        this.drink = drink;
        this.quantity = quantity;
        this.priceWithoutDiscount = round(drink.getPurchasePrice() * PriceUtil.getMarkup());
        this.priceWithDiscount = round(drink.getPurchasePrice() * PriceUtil.WHOLESALE_MARKUP);
        this.averageItemPrice = getAverageItemPrice();
    }

    public double getPriceWithDiscount() {
        return priceWithDiscount;
    }

    public Drink getDrink() {
        return drink;
    }

    public void setDrink(Drink drink) {
        this.drink = drink;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPriceWithoutDiscount() {
        return priceWithoutDiscount;
    }

    public double getAverageItemPrice() {
        if (quantity <= 2) {
            averageItemPrice = priceWithoutDiscount;
        } else {
            double priceForTwoPieces = priceWithoutDiscount * 2;
            double priceForOtherPieces = priceWithDiscount * (quantity - 2);
            averageItemPrice = round((priceForTwoPieces + priceForOtherPieces) / quantity);
        }
        return averageItemPrice;
    }

    public void setAverageItemPrice(double averageItemPrice) {
        this.averageItemPrice = averageItemPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ShortenedOrderItem)) return false;
        ShortenedOrderItem that = (ShortenedOrderItem) o;
        return getQuantity() == that.getQuantity() &&
                getDrink().equals(that.getDrink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getDrink(), getQuantity());
    }
}
