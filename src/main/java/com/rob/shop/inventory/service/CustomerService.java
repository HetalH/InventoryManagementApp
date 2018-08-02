package com.rob.shop.inventory.service;

import com.rob.shop.inventory.api.model.CustomerDto;
import com.rob.shop.inventory.api.model.CustomerRequestDto;
import com.rob.shop.inventory.api.model.ProductDto;
import com.rob.shop.inventory.entity.Customer;
import com.rob.shop.inventory.entity.Product;
import com.rob.shop.inventory.service.exception.CustomerAlreadyExist;
import com.rob.shop.inventory.service.exception.CustomerNotExist;
import com.rob.shop.inventory.service.exception.ProductNotExist;
import com.rob.shop.inventory.service.transformer.CustomerTransformer;
import com.rob.shop.inventory.service.transformer.ProductTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private List<Customer> customers = new ArrayList<>();
    private Long custIdCount = 1L;

    public boolean addCustomer(CustomerRequestDto customerDto) throws CustomerAlreadyExist {

        Customer customer = CustomerTransformer.transform(customerDto);

        if(customers.contains(customer))
        {
            throw new CustomerAlreadyExist(customer.getName(), customer.getPhoneNo());
        }
        else
        {
            customer.setCustId(custIdCount++);
            customers.add(customer);
        }
        return true;
  }
    public List<CustomerDto> getAllCustomer()  throws CustomerNotExist
    {
        List<CustomerDto> customerDtos = new ArrayList<>();

        for(Customer customer : customers)
        {
            CustomerDto customerDto = getCustomerById(customer.getCustId());
            customerDtos.add(customerDto);
        }

        return customerDtos;
    }
    public CustomerDto getCustomerById(Long custId) throws CustomerNotExist {

        Optional<Customer> customerById = customers.stream()
                .filter(customer -> customer.getCustId().equals(custId))
                .findFirst();
        if(customerById.isPresent()) {

            Customer customer = customerById.get();
            CustomerDto customerDto = CustomerTransformer.transformDto(customer);
            return customerDto;
        }
        else {

            throw new CustomerNotExist(custId);

        }
    }
}
