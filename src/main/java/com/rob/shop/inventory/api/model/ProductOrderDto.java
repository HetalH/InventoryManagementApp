package com.rob.shop.inventory.api.model;

public class ProductOrderDto {
    private Integer quantity;
    private String name;


    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
