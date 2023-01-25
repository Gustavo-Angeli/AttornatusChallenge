package com.gusta.people.mapper.mocks;

import com.gusta.people.data.*;
import com.gusta.people.model.*;

public class MockAddress {

    public Address mockEntity() {
        return mockEntity(0);
    }

    public AddressVO mockVO() {
        return mockVO(0);
    }

    private Address mockEntity(Integer id) {
        Address address = new Address();
        address.setId(id.longValue());
        address.setMainAddress(false);
        address.setCity("city");
        address.setNumber(123);
        address.setZipCode("95555");
        return address;
    }

    private AddressVO mockVO(Integer id) {
        AddressVO address = new AddressVO();
        address.setId(id.longValue());
        address.setMainAddress(false);
        address.setCity("city");
        address.setNumber(123);
        address.setZipCode("95555");
        return address;
    }

}
