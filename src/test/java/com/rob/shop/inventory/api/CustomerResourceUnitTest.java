package com.rob.shop.inventory.api;

import com.rob.shop.inventory.api.model.CustomerDto;
import com.rob.shop.inventory.api.model.CustomerRequestDto;
import com.rob.shop.inventory.service.CustomerService;
import com.rob.shop.inventory.service.exception.CustomerAlreadyExist;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@WebMvcTest(CustomerResource.class)
public class CustomerResourceUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerServiceMock;

    private String GETALLCUSTOMERJSON = "[{\"id\":1,\"name\":\"Customer1\",\"phoneNo\":\"9988454323\"}]";

    private String CUSTOMERDTOJSON = "{" +
            "  \"name\": \"Anand\"," +
            "  \"phoneNo\": \"9886947518\"" +
            "}";
    private String INVALIDCUSTOMERDTOJSON = "{" +
            "  \"name\": \"Anand\"," +
            "  \"phoneNo\": \"988694\"" +
            "}";
    @Test
    public void getAllCustomer_test() throws Exception {
        List<CustomerDto> customerDtos = new ArrayList<>();
        CustomerDto customerDto = new CustomerDto();
        customerDto.setId(1L);
        customerDto.setName("Customer1");
        customerDto.setPhoneNo("9988454323");
        customerDtos.add(customerDto);

        Mockito.when(customerServiceMock.getAllCustomer()).thenReturn(customerDtos);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/customer").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        Assert.assertEquals(content,GETALLCUSTOMERJSON);

    }

    @Test
    public void addCustomer_test() throws Exception {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setName("Customer1");
        customerRequestDto.setPhoneNo("9988454323");
        Mockito.when(customerServiceMock.addCustomer(customerRequestDto)).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer",customerRequestDto).accept(MediaType.APPLICATION_JSON).content(CUSTOMERDTOJSON)
                .contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.CREATED.value(),result.getResponse().getStatus());
    }
    @Test
    public void addCustomer_invalid_phoneno_test() throws Exception {
        CustomerRequestDto customerRequestDto = new CustomerRequestDto();
        customerRequestDto.setName("Customer1");
        customerRequestDto.setPhoneNo("998845");
        Mockito.when(customerServiceMock.addCustomer(customerRequestDto)).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/customer").accept(MediaType.APPLICATION_JSON).content(INVALIDCUSTOMERDTOJSON)
                .contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.BAD_REQUEST.value(),result.getResponse().getStatus());
    }


}