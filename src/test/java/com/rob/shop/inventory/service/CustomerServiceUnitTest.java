package com.rob.shop.inventory.service;

import com.rob.shop.inventory.api.model.CustomerDto;
import com.rob.shop.inventory.api.model.CustomerRequestDto;
import com.rob.shop.inventory.service.exception.CustomerAlreadyExist;
import com.rob.shop.inventory.service.exception.CustomerNotExist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class CustomerServiceUnitTest {

    private CustomerService customerService;
    @Before
    public void setUp() {
        customerService = new CustomerService();
    }
    @Test
    public void add_customer_test() throws CustomerAlreadyExist {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setName("Customer1");
        customerRequestDto.setPhoneNo("9988454323");

        boolean result = customerService.addCustomer(customerRequestDto);
        Assert.assertEquals(result,true);

    }
    @Test(expected = CustomerAlreadyExist.class)
    public void add_customer_throw_exception_test() throws CustomerAlreadyExist {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setName("Customer1");
        customerRequestDto.setPhoneNo("9988454323");
        customerService.addCustomer(customerRequestDto);

        customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setName("Customer1");
        customerRequestDto.setPhoneNo("9988454323");
        customerService.addCustomer(customerRequestDto);

    }

    @Test
    public void getAllCustomer_test() throws CustomerAlreadyExist, CustomerNotExist {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setName("Customer1");
        customerRequestDto.setPhoneNo("9988454323");

        customerService.addCustomer(customerRequestDto);

       List<CustomerDto> customerDtos = customerService.getAllCustomer();
       Assert.assertEquals(1,customerDtos.size());

    }


    @Test
    public void getCustomerById_test() throws CustomerAlreadyExist, CustomerNotExist {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setName("Customer1");
        customerRequestDto.setPhoneNo("9988454323");

        customerService.addCustomer(customerRequestDto);

        CustomerDto result = customerService.getCustomerById(1L);
        Assert.assertEquals("Customer1",result.getName());

    }
    @Test(expected = CustomerNotExist.class)
    public void getCustomerById_throw_exception_test() throws CustomerNotExist {
        CustomerDto result = customerService.getCustomerById(2L);

    }
}