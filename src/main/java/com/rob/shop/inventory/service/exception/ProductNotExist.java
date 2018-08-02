package com.rob.shop.inventory.service.exception;

public class ProductNotExist extends Exception{

    public ProductNotExist(String name) {
        super("Product with name:" + name + " not exist");
    }
}