package com.myshop.domain;

import javax.persistence.*;

@Entity
@Table(name = "nonalcoholicdrinks")
@DiscriminatorValue("n")
public class NonAlcoholicDrink extends Drink{
    @Column(name = "type")
    @Enumerated(EnumType.STRING)
    private NonAlcoholicGroup group;
    @Column
    private String composition;

    public NonAlcoholicDrink() {
    }

    public NonAlcoholicDrink(String name, double purchasePrice, double volume, int quantity, NonAlcoholicGroup group, String composition) {
        this(null, name, purchasePrice, volume, quantity, group, composition);
    }

    public NonAlcoholicDrink(Integer id, String name, double purchasePrice, double volume, int quantity, NonAlcoholicGroup group, String composition) {
        super(id, name, purchasePrice, volume, quantity);
        this.group = group;
        this.composition = composition;
    }

    public NonAlcoholicGroup getGroup() {
        return group;
    }

    public void setGroup(NonAlcoholicGroup group) {
        this.group = group;
    }

    public String getComposition() {
        return composition;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    @Override
    public String toString() {
        return "NonAlcoholicDrink{" +
                "group=" + group +
                ", composition='" + composition + '\'' +
                ", name='" + name + '\'' +
                ", purchasePrice=" + purchasePrice +
                ", volume=" + volume +
                ", quantity=" + quantity +
                '}';
    }
}
