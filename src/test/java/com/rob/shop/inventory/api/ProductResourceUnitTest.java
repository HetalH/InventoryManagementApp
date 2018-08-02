package com.rob.shop.inventory.api;

import com.rob.shop.inventory.api.model.ProductDto;
import com.rob.shop.inventory.service.ProductService;
import com.rob.shop.inventory.service.exception.ProductNotExist;
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
@WebMvcTest(ProductResource.class)
public class ProductResourceUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productServiceMock;

    private String PRODUCTDTOJSON = "{" +
            "  \"availableServings\": 10," +
            "  \"description\": \"bbb\"," +
            "  \"name\": \"bbb\"" +
            "}";


    private String GETALLPRODUCTJSON="[{\"name\":\"aaa\",\"description\":\"aaa\",\"availableServings\":8}]";
    private String GETALLPRODUCTREPORTJSON="[{\"coffeVariety\":\"aaa\",\"totalServingForTheDay\":8,\"totalServingSoldForTheDay\":5}]";

    @Test
    public void getAllProducts_test() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);
        productDtos.add(productDto);

        Mockito.when(productServiceMock.getAllProduct()).thenReturn(productDtos);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/product").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        Assert.assertEquals(content,GETALLPRODUCTJSON);
    }

    @Test
    public void addProduct_test() throws Exception {
        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);
        Mockito.when(productServiceMock.addProduct(productDto)).thenReturn(true);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/product",productDto).accept(MediaType.APPLICATION_JSON).content(PRODUCTDTOJSON)
                .contentType(MediaType.APPLICATION_JSON);;

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        Assert.assertEquals(HttpStatus.CREATED.value(),result.getResponse().getStatus());
    }

    @Test
    public void getDailyReport_test() throws Exception {
        List<ProductDto> productDtos = new ArrayList<>();
        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);
        productDto.setSoldServings(5);
        productDtos.add(productDto);

        Mockito.when(productServiceMock.getAllProduct()).thenReturn(productDtos);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get(
                "/product/report/daily").accept(
                MediaType.APPLICATION_JSON);

        MvcResult result = mockMvc.perform(requestBuilder).andReturn();
        String content = result.getResponse().getContentAsString();
        Assert.assertEquals(HttpStatus.OK.value(),result.getResponse().getStatus());
        Assert.assertEquals(content,GETALLPRODUCTREPORTJSON);
    }
}