package com.rob.shop.inventory.api;

import com.rob.shop.inventory.aop.LogMessage;
import com.rob.shop.inventory.api.model.CustomerDto;
import com.rob.shop.inventory.api.model.CustomerRequestDto;
import com.rob.shop.inventory.service.CustomerService;
import com.rob.shop.inventory.service.exception.CustomerAlreadyExist;
import com.rob.shop.inventory.service.exception.CustomerNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class CustomerResource {

    @Autowired
    private CustomerService customerService;

    @RequestMapping(value = "/customer", method = RequestMethod.GET, produces = {"application/json"})
    @LogMessage
    public ResponseEntity<List<CustomerDto>> getAllCustomer() throws CustomerNotExist {
        List<CustomerDto> customerDtos = customerService.getAllCustomer();
        return new ResponseEntity<>(customerDtos,HttpStatus.OK);
    }

    @RequestMapping(value = "/customer", method = RequestMethod.POST, consumes = {"application/json"})
    public ResponseEntity<Void> addCustomer(@Valid @RequestBody CustomerRequestDto customerDto) throws CustomerAlreadyExist {
        customerService.addCustomer(customerDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
