package com.rob.shop.inventory.service.transformer;

import com.rob.shop.inventory.api.model.CustomerDto;
import com.rob.shop.inventory.api.model.CustomerRequestDto;
import com.rob.shop.inventory.api.model.ProductDto;
import com.rob.shop.inventory.entity.Customer;
import com.rob.shop.inventory.entity.Product;

public class CustomerTransformer {

    public static Customer transform(CustomerRequestDto customerDto) {

        Customer customer = new Customer(customerDto.getName(), customerDto.getPhoneNo());
        return customer;
    }

    public static CustomerDto transformDto(Customer customer){

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(customer.getCustId());
        customerDto.setName(customer.getName());
        customerDto.setPhoneNo(customer.getPhoneNo());
        return customerDto;
    }
}
