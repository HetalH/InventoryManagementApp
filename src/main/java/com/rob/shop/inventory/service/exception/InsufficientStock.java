package com.rob.shop.inventory.service.exception;

public class InsufficientStock extends Exception {

    public InsufficientStock(String message)
    {
        super(message);
    }
}
