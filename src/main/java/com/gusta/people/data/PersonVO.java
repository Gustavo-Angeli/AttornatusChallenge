package com.gusta.people.data;

import com.gusta.people.model.*;

import javax.persistence.*;
import java.util.*;

public class PersonVO {

    private Long id;
    private String name;
    private String dateOfBirth;
    private List<Address> address;

    public PersonVO() {
    }

    public PersonVO(String name, String dateOfBirth) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PersonVO personVO = (PersonVO) o;
        return Objects.equals(id, personVO.id) && Objects.equals(name, personVO.name) && Objects.equals(dateOfBirth, personVO.dateOfBirth) && Objects.equals(address, personVO.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, dateOfBirth, address);
    }
}
