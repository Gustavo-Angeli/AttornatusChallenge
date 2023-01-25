package com.gusta.people.controllers;

import com.gusta.people.data.*;
import com.gusta.people.services.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/api/person/")
public class PersonController {

    @Autowired
    PersonServices services;

    @GetMapping("findPerson/{id}")
    public PersonVO findPerson(@PathVariable(value = "id") Long id) {
        return services.findPersonById(id);
    }

    @GetMapping("findAll")
    public List<PersonVO> findAll() {
        return services.findAll();
    }

    @PostMapping("createPerson")
    public PersonVO createPerson(@RequestBody PersonVO vo) {
        return services.createPerson(vo);
    }

    @PutMapping("update/{id}")
    public PersonVO updatePerson(@PathVariable(value = "id") Long id, @RequestBody PersonVO vo) throws Exception {
        return services.updatePersonById(id, vo);
    }

    @PutMapping("addAddress/{id}")
    public AddressVO addAddressToPerson(@PathVariable(value = "id") Long id, @RequestBody AddressVO vo) throws Exception {
        return services.addAddressToPerson(id, vo);
    }

    @GetMapping("getAllAddresses/{id}")
    public List<AddressVO> getPersonAddresses(@PathVariable(value = "id") Long id) {
        return services.getPersonAddresses(id);
    }
    @GetMapping("getMainAddress/{id}")
    public AddressVO getMainAddress(@PathVariable(value = "id") Long id) {
        return services.getMainAddress(id);
    }

}
