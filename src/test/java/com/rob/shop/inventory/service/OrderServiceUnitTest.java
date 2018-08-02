package com.rob.shop.inventory.service;

import com.rob.shop.inventory.api.model.*;
import com.rob.shop.inventory.service.exception.CustomerNotExist;
import com.rob.shop.inventory.service.exception.InsufficientStock;
import com.rob.shop.inventory.service.exception.ProductNotExist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class OrderServiceUnitTest {

    private OrderService orderService;

    private ProductService productServiceMock;
    private CustomerService customerServiceMock;

    @Before
    public void setUp() throws Exception {

        orderService = new OrderService();
        productServiceMock = Mockito.mock(ProductService.class);
        customerServiceMock = Mockito.mock(CustomerService.class);

        orderService = new OrderService();
        ReflectionTestUtils.setField(orderService, "productService",productServiceMock);
        ReflectionTestUtils.setField(orderService, "customerService",customerServiceMock);
    }

    @Test
    public void addOrder_test() throws CustomerNotExist, ProductNotExist, InsufficientStock {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("Customer1");
        customerDto.setPhoneNo("9988454323");

        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);

        Mockito.when(customerServiceMock.getCustomerById(1L)).thenReturn(customerDto);
        Mockito.when(productServiceMock.getProductByName("aaa")).thenReturn(productDto);

        OrderDto orderDto = new OrderDto();
        orderDto.setCustId(1L);
        List<ProductOrderDto> productOrderDtos = new ArrayList<>();
        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setName("aaa");
        productOrderDto.setQuantity(2);
        productOrderDtos.add(productOrderDto);

        orderDto.setProductOrders(productOrderDtos);

        ReceiptDto receiptDto = orderService.addOrder(orderDto);
        Assert.assertEquals(1,receiptDto.getOrder().size());
        Mockito.verify(customerServiceMock).getCustomerById(1L);
        Mockito.verify(productServiceMock).getProductByName("aaa");

    }
    @Test(expected = InsufficientStock.class)
    public void addOrder_throw_exception_test() throws CustomerNotExist, ProductNotExist, InsufficientStock {

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("Customer1");
        customerDto.setPhoneNo("9988454323");

        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);

        Mockito.when(customerServiceMock.getCustomerById(1L)).thenReturn(customerDto);
        Mockito.when(productServiceMock.getProductByName("aaa")).thenReturn(productDto);

        OrderDto orderDto = new OrderDto();
        orderDto.setCustId(1L);
        List<ProductOrderDto> productOrderDtos = new ArrayList<>();
        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setName("aaa");
        productOrderDto.setQuantity(10);
        productOrderDtos.add(productOrderDto);

        orderDto.setProductOrders(productOrderDtos);

        ReceiptDto receiptDto = orderService.addOrder(orderDto);


    }


    @Test
    public void checkProductAvailableStock() {
    }
}