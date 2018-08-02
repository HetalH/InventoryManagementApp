package com.rob.shop.inventory.service;

import com.rob.shop.inventory.api.model.*;
import com.rob.shop.inventory.service.exception.CustomerNotExist;
import com.rob.shop.inventory.service.exception.InsufficientStock;
import com.rob.shop.inventory.service.exception.ProductNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private ProductService productService;

    @Autowired
    private CustomerService customerService;

    private Long orderIdCounter = 1L;

    public ReceiptDto addOrder(OrderDto orderDto) throws CustomerNotExist, ProductNotExist, InsufficientStock {

        CustomerDto customerDto = customerService.getCustomerById(orderDto.getCustId());
        List<ProductOrderDto> productOrderDtos = orderDto.getProductOrders();
        checkProductAvailableStock(productOrderDtos);

            ReceiptDto receiptDto = new ReceiptDto(orderIdCounter,
                    customerDto,
                    productOrderDtos,
                    LocalDateTime.now());

            orderIdCounter++;

           return  receiptDto;

    }
    public void checkProductAvailableStock(List<ProductOrderDto> productOrderDtos) throws ProductNotExist, InsufficientStock {

        StringBuilder sb = new StringBuilder();
        List<ProductDto> productDtos = new ArrayList<>();
        for(ProductOrderDto productOrderDto : productOrderDtos) {
            ProductDto productDto = productService.getProductByName(productOrderDto.getName());
            Integer availableServing = productDto.getAvailableServings()-productDto.getSoldServings();

            if(productOrderDto.getQuantity() <= availableServing)
            {
                Integer soldServing = productDto.getSoldServings() + productOrderDto.getQuantity();
                productDto.setAvailableServings(soldServing);
                productDtos.add(productDto);

            }
            else
            {

                sb.append("Product:" + productDto.getName() + " available servings only:" + availableServing);
            }

        }
        if(!sb.toString().isEmpty())
        {
            throw new InsufficientStock(sb.toString());
        }
        else
        {
            for(ProductDto productDto : productDtos) {
                productService.saveProduct(productDto);
            }
        }
    }


}
