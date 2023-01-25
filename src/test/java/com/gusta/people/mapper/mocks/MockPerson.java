package com.gusta.people.mapper.mocks;

import com.gusta.people.data.*;
import com.gusta.people.model.*;

public class MockPerson {

    public Person mockEntity() {
        return mockEntity(0);
    }

    public PersonVO mockVO() {
        return mockVO(0);
    }

    private Person mockEntity(Integer id) {
        Person person = new Person();
        person.setId(id.longValue());
        person.setName("Gustavo");
        person.setDateOfBirth("01032005");
        return person;
    }

    private PersonVO mockVO(Integer id) {
        PersonVO person = new PersonVO();
        person.setId(id.longValue());
        person.setName("Gustavo");
        person.setDateOfBirth("01032005");
        return person;
    }

}
