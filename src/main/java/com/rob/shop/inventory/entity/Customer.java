package com.rob.shop.inventory.entity;

import java.util.Objects;

public class Customer {

    private Long custId;
    private String name;
    private String phoneNo;

    public Customer() {

    }
    public Customer(String name, String phoneNo){
        this.name = name;
        this.phoneNo = phoneNo;
    }

    public String getName() {
        return name;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public Long getCustId() {
        return custId;
    }

    public void setCustId(Long custId) {
        this.custId = custId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return Objects.equals(name, customer.name) &&
                Objects.equals(phoneNo, customer.phoneNo);
    }

    @Override
    public int hashCode() {

        return Objects.hash(name, phoneNo);
    }
}
