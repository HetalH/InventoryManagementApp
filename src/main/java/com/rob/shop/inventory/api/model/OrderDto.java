package com.rob.shop.inventory.api.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import java.util.List;

public class OrderDto {
    @NotEmpty(message = "CustomerId can't be blank")
    private Long custId;

   List<ProductOrderDto> productOrders;

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    public List<ProductOrderDto> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(List<ProductOrderDto> productOrders) {
        this.productOrders = productOrders;
    }
}
