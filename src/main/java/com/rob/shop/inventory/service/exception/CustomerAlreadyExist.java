package com.rob.shop.inventory.service.exception;

public class CustomerAlreadyExist extends  Exception {

    public CustomerAlreadyExist(String name,String phoneNo) {
        super("Customer with name:" + name + " and " + phoneNo +" phoneNo already exist");
    }
}
