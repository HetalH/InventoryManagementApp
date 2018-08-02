package com.rob.shop.inventory.api;

import com.rob.shop.inventory.api.model.CustomerDto;
import com.rob.shop.inventory.api.model.OrderDto;
import com.rob.shop.inventory.api.model.ProductOrderDto;
import com.rob.shop.inventory.api.model.ReceiptDto;
import com.rob.shop.inventory.service.OrderService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderResource.class)
public class OrderResourceUnitTest {

    @MockBean
    private OrderService orderServiceMock;

    @Autowired
    private MockMvc mockMvc;

    private String ORDERDTOJSON ="{" +
            "  \"custId\": 1," +
            "  \"productOrders\": [" +
            "    {\n" +
            "      \"name\": \"aaa\"," +
            "      \"quantity\": 5" +
            "    }"+
            "  ]" +
            "}";

    @Test
    public void addOrder() throws Exception {
        OrderDto orderDto = new OrderDto();
        List<ProductOrderDto> productOrderDtos = new ArrayList<>();
        ProductOrderDto productOrderDto = new ProductOrderDto();
        productOrderDto.setName("aaa");
        productOrderDto.setQuantity(3);
        productOrderDtos.add(productOrderDto);
        orderDto.setProductOrders(productOrderDtos);
        orderDto.setCustId(1L);

        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("Customer1");
        customerDto.setPhoneNo("9988454323");

        ReceiptDto receiptDto = new ReceiptDto(1L,customerDto,productOrderDtos, LocalDateTime.now());


        Mockito.when(orderServiceMock.addOrder(orderDto)).thenReturn(receiptDto);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/order").accept(MediaType.APPLICATION_JSON).content(ORDERDTOJSON)
                .contentType(MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
    }
}