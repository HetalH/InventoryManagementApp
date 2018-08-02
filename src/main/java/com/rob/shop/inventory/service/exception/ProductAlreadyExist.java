package com.rob.shop.inventory.service.exception;

public class ProductAlreadyExist extends Exception{

    public ProductAlreadyExist(String name) {
        super("Product with name:" + name + " already exist");
    }
}
