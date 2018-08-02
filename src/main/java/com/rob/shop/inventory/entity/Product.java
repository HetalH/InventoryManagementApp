package com.rob.shop.inventory.entity;

import java.util.Objects;

public class Product {
    private String name;
    private String description;
    private Integer availableServings;
    private Integer soldServings = 0;

    public Product() {

    }
    public Product(String name, String description, Integer availableServings) {
        this.name = name;
        this.description = description;
        this.availableServings = availableServings;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Integer getAvailableServings() {
        return availableServings;
    }

     public Integer getSoldServings() {
        return soldServings;
    }

    public void setSoldServings(Integer soldServings) {
        this.soldServings = soldServings;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name);
    }
}
