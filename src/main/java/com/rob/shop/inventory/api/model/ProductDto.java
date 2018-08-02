package com.rob.shop.inventory.api.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

public class ProductDto {

    @NotEmpty(message = "name can't be empty")
    private String name;

    @NotEmpty(message = "description can't be empty")
    private String description;

    @Positive
    private Integer availableServings;
    @JsonIgnore
    private Integer soldServings = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getAvailableServings() {
        return availableServings;
    }

    public void setAvailableServings(Integer availableServings) {
        this.availableServings = availableServings;
    }

    public Integer getSoldServings() {
        return soldServings;
    }

    public void setSoldServings(Integer soldServings) {
        this.soldServings = soldServings;
    }
}
