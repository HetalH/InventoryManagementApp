package com.rob.shop.inventory.service;

import com.rob.shop.inventory.api.model.ProductDto;
import com.rob.shop.inventory.service.exception.ProductAlreadyExist;
import com.rob.shop.inventory.service.exception.ProductNotExist;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ProductServiceUnitTest {

    private ProductService productService;

    @Before
    public void setUp() throws Exception {
        productService = new ProductService();
    }
    @Test
    public void addProduct_Test() throws ProductAlreadyExist {
        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);

        boolean result = productService.addProduct(productDto);
        Assert.assertEquals(true,result);
    }

    @Test(expected = ProductAlreadyExist.class)
    public void addProduct_throw_exception_test() throws ProductAlreadyExist {
        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);

        boolean result = productService.addProduct(productDto);

        productDto = new ProductDto();
        productDto.setName("aaa");

        productService.addProduct(productDto);

    }
    @Test
    public void getAllProduct_test() throws ProductAlreadyExist, ProductNotExist {
        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);

        productService.addProduct(productDto);

       List<ProductDto> productDtos = productService.getAllProduct();
       Assert.assertEquals(1,productDtos.size());
    }
    @Test
    public void getProductByName_test() throws ProductAlreadyExist, ProductNotExist {
        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);

        productService.addProduct(productDto);

        ProductDto productBean = productService.getProductByName("aaa");
        assertEquals("aaa",productBean.getName());
        assertEquals("aaa",productBean.getDescription());
        assertEquals(new Integer(8),productBean.getAvailableServings());
    }
    @Test(expected = ProductNotExist.class)
    public void getProductByName_throw_exception_test() throws ProductNotExist {

        ProductDto productBean = productService.getProductByName("bbb");

    }
    @Test
    public void saveProduct_test() throws ProductAlreadyExist {
        ProductDto productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setDescription("aaa");
        productDto.setAvailableServings(8);

        productService.addProduct(productDto);

        productDto = new ProductDto();
        productDto.setName("aaa");
        productDto.setAvailableServings(3);

        boolean result = productService.saveProduct(productDto);

        Assert.assertEquals(true,result);


    }
}