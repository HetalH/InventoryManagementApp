package com.rob.shop.inventory.service;

import com.rob.shop.inventory.api.model.ProductDto;
import com.rob.shop.inventory.entity.Product;
import com.rob.shop.inventory.service.exception.ProductAlreadyExist;
import com.rob.shop.inventory.service.exception.ProductNotExist;
import com.rob.shop.inventory.service.transformer.ProductTransformer;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    private List<Product> productList = new ArrayList<>();

    public boolean addProduct(ProductDto productDto) throws ProductAlreadyExist {

        Product product = ProductTransformer.transform(productDto);
        if(productList.contains(product))
        {
            throw new ProductAlreadyExist(productDto.getName());
        }
        else
        {
           productList.add(product);
        }
        return true;
    }
    public List<ProductDto> getAllProduct()  throws ProductNotExist
    {
        List<ProductDto> productDtos = new ArrayList<>();

        for(Product product : productList)
        {
            ProductDto productDto = getProductByName(product.getName());
            productDtos.add(productDto);
        }

        return productDtos;
    }
    public ProductDto getProductByName(String name) throws ProductNotExist {

        Optional<Product> productByName = productList.stream()
                .filter(product -> product.getName().equals(name))
                .findFirst();
        if(productByName.isPresent()) {

            Product product = productByName.get();
            ProductDto productDto = ProductTransformer.transformDto(product);
            return productDto;
        }
        else {

            throw new ProductNotExist(name);

        }
    }
    public boolean saveProduct(ProductDto productDto) {

        Optional<Product> productByName = productList.stream()
                .filter(product -> product.getName().equals(productDto.getName()))
                .findFirst();
        Product product = productByName.get();
        product.setSoldServings(productDto.getAvailableServings());
        return true;
    }
}
