package com.rob.shop.inventory.api.model;

public class ProductReportDto {
    private String coffeVariety;
    private Integer totalServingForTheDay;
    private Integer totalServingSoldForTheDay;

    public ProductReportDto(String coffeVariety, Integer totalServingForTheDay, Integer totalServingSoldForTheDay) {
        this.coffeVariety = coffeVariety;
        this.totalServingForTheDay = totalServingForTheDay;
        this.totalServingSoldForTheDay = totalServingSoldForTheDay;
    }

    public String getCoffeVariety() {
        return coffeVariety;
    }

    public Integer getTotalServingForTheDay() {
        return totalServingForTheDay;
    }

    public Integer getTotalServingSoldForTheDay() {
        return totalServingSoldForTheDay;
    }

}
