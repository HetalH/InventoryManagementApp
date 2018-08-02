package com.rob.shop.inventory.api.model;

import com.rob.shop.inventory.api.model.validator.IsValidPhoneNo;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class CustomerRequestDto {

    @NotBlank(message = "Customer name can't be empty.")
    private String name;
    @NotNull
    @IsValidPhoneNo
    private String phoneNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }
 }
