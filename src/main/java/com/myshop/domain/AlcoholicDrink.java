package com.myshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "alcoholicdrinks")
@DiscriminatorValue("a")
public class AlcoholicDrink extends Drink{
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private AlcoholicGroup group;
    @Column(name = "abv")
    private double ABV;

    public AlcoholicDrink() {
    }

    public AlcoholicDrink(String name, double purchasePrice, double volume, int quantity, AlcoholicGroup group, double ABV) {
        this(null, name, purchasePrice, volume, quantity,group, ABV);
    }

    public AlcoholicDrink(Integer id, String name, double purchasePrice, double volume, int quantity, AlcoholicGroup group, double ABV) {
        super(id, name, purchasePrice, volume, quantity);
        this.group = group;
        this.ABV = ABV;
    }

    public AlcoholicGroup getGroup() {
        return group;
    }

    public void setGroup(AlcoholicGroup group) {
        this.group = group;
    }

    public double getABV() {
        return ABV;
    }

    public void setABV(double ABV) {
        this.ABV = ABV;
    }

    @Override
    public String toString() {
        return "AlcoholicDrink{" +
                "group=" + group +
                ", ABV=" + ABV +
                ", name='" + name + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", volume=" + volume +
                ", quantity=" + quantity +
                '}';
    }
}
