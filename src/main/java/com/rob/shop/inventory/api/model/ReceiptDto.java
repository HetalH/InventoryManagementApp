package com.rob.shop.inventory.api.model;

import java.time.LocalDateTime;
import java.util.List;

public class ReceiptDto {
    private Long orderId;
    private CustomerDto customer;
    private List<ProductOrderDto> order;
    private LocalDateTime orderDate;


    public ReceiptDto(Long orderId, CustomerDto customerDto,List<ProductOrderDto> productDtoList, LocalDateTime orderDate) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.customer = customerDto;
        this.order = productDtoList;
    }

    public Long getOrderId() {
        return orderId;
    }

    public CustomerDto getCustomer() {
        return customer;
    }

    public List<ProductOrderDto> getOrder() {
        return order;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }
   }
