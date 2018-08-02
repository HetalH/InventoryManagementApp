package com.rob.shop.inventory.api;

import com.rob.shop.inventory.api.model.ProductDto;
import com.rob.shop.inventory.api.model.ProductReportDto;
import com.rob.shop.inventory.service.ProductService;
import com.rob.shop.inventory.service.exception.ProductAlreadyExist;
import com.rob.shop.inventory.service.exception.ProductNotExist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductResource {
    @Autowired
    private ProductService productService;

   @RequestMapping(value = "/product", method = RequestMethod.GET, produces = {"application/json"})
   public ResponseEntity<List<ProductDto>> getAllProducts() throws ProductNotExist {

        List<ProductDto> productDtos = productService.getAllProduct();
        return new ResponseEntity<>(productDtos,HttpStatus.OK);

    }


    @RequestMapping(value = "/product", method = RequestMethod.POST, produces = {"application/json"})
    public ResponseEntity<Void> addProduct(@Valid @RequestBody ProductDto productDto) throws ProductAlreadyExist {

        productService.addProduct(productDto);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/product/report/daily", method = RequestMethod.GET, produces = {"application/json"})
    public ResponseEntity<List<ProductReportDto>> getDailyReport() throws ProductNotExist {

        List<ProductDto> productDtos = productService.getAllProduct();

        List<ProductReportDto> productReportDtos = new ArrayList<>();

        for(ProductDto productDto : productDtos) {
            ProductReportDto productReportDto = new ProductReportDto(productDto.getName(),productDto.getAvailableServings(),productDto.getSoldServings());
            productReportDtos.add(productReportDto);
        }

        return new ResponseEntity<>(productReportDtos,HttpStatus.OK);

    }
}
