package com.rob.shop.inventory.service.transformer;

import com.rob.shop.inventory.api.model.ProductDto;
import com.rob.shop.inventory.entity.Product;

public class ProductTransformer {
    public static Product transform(ProductDto productDto) {
        Product product = new Product(productDto.getName(),productDto.getDescription(),productDto.getAvailableServings());
        return product;
    }
    public static ProductDto transformDto(Product product){

        ProductDto productDto = new ProductDto();
        productDto.setName(product.getName());
        productDto.setDescription(product.getDescription());
        productDto.setAvailableServings(product.getAvailableServings());
        productDto.setSoldServings(product.getSoldServings());
        return productDto;
    }
}
