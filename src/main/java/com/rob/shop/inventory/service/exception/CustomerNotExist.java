package com.rob.shop.inventory.service.exception;

public class CustomerNotExist extends Exception{

    public CustomerNotExist(Long id) {
        super("Customer with Id:" + id + " not exist");
    }
}
