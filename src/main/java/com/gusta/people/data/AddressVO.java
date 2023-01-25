package com.gusta.people.data;

import javax.persistence.*;
import java.util.*;

public class AddressVO {

    private Long id;
    private String street;
    private String zipCode;
    private Integer number;
    private Boolean mainAddress;
    private String city;

    public AddressVO() {
    }

    public AddressVO(Long id, String street, String zipCode, Integer number, Boolean mainAddress, String city) {
        this.id = id;
        this.street = street;
        this.zipCode = zipCode;
        this.number = number;
        this.mainAddress = mainAddress;
        this.city = city;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Boolean getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(Boolean mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AddressVO address = (AddressVO) o;
        return Objects.equals(id, address.id) && Objects.equals(street, address.street) && Objects.equals(zipCode, address.zipCode) && Objects.equals(number, address.number) && Objects.equals(mainAddress, address.mainAddress) && Objects.equals(city, address.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, street, zipCode, number, mainAddress, city);
    }
}
