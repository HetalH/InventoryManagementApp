package com.rob.shop.inventory.api;

import com.fasterxml.jackson.annotation.JsonView;
import com.rob.shop.inventory.api.model.OrderDto;
import com.rob.shop.inventory.api.model.ReceiptDto;
import com.rob.shop.inventory.api.view.ProductProfile;
import com.rob.shop.inventory.service.OrderService;
import com.rob.shop.inventory.service.exception.CustomerNotExist;
import com.rob.shop.inventory.service.exception.InsufficientStock;
import com.rob.shop.inventory.service.exception.ProductNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderResource
{
    @Autowired
    private OrderService orderService;

    @RequestMapping(value = "/order", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<ReceiptDto> addOrder(@RequestBody OrderDto orderDto) throws CustomerNotExist, ProductNotExist, InsufficientStock {

        ReceiptDto receiptDto = orderService.addOrder(orderDto);

        return new ResponseEntity<>(receiptDto, HttpStatus.OK);

    }
}
