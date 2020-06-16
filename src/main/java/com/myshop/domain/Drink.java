package com.myshop.domain;

import javax.persistence.*;
import java.util.Objects;

@NamedQueries({
        @NamedQuery(name = Drink.NO_SORT, query = "SELECT d FROM Drink d"),
        @NamedQuery(name = Drink.SORTED, query = "SELECT d FROM Drink d ORDER BY d.name"),
        @NamedQuery(name = Drink.SEARCH_BY_PART_OF_NAME, query = "SELECT d FROM Drink d WHERE LOWER (d.name) LIKE CONCAT('%', LOWER(?1),'%') ORDER BY d.name")
})
@Entity
@Table(name = "drinks")
@DiscriminatorColumn(name = "Drink_type")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Drink extends AbstractBaseEntity{
    public static final String NO_SORT = "Drink.getAll";
    public static final String SORTED = "Drink.getAllSorted";
    public static final String SEARCH_BY_PART_OF_NAME = "Drink.filteredByName";

    @Column
    protected String name;
    @Column(name="purchaseprice")
    protected double purchasePrice;
    @Column
    protected double volume;
    @Column
    protected int quantity;

    public Drink() {
    }

    public Drink(Integer id, String name, double purchasePrice, double volume, int quantity) {
        super(id);
        this.name = name;
        this.purchasePrice = purchasePrice;
        this.volume = volume;
        this.quantity = quantity;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Drink)) return false;
        Drink drink = (Drink) o;
        return Double.compare(drink.getPurchasePrice(), getPurchasePrice()) == 0 &&
                Double.compare(drink.getVolume(), getVolume()) == 0 &&
                getName().equals(drink.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getPurchasePrice(), getVolume());
    }
}
