package com.gusta.people.services;

import com.gusta.people.data.*;
import com.gusta.people.exceptions.*;
import com.gusta.people.mapper.*;
import com.gusta.people.model.*;
import com.gusta.people.repositories.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.text.*;
import java.util.*;
import java.util.logging.*;

@Service
public class PersonServices {

    private Logger logger = Logger.getLogger(PersonServices.class.getName());
    @Autowired
    private PersonRepository personRepository;
    @Autowired
    private AddressRepository addressRepository;

    public PersonVO findPersonById(Long id) {
        if (id == null) throw new NullPointerException("Please insert a valid value");
        if (id.toString().isBlank()) throw new IllegalArgumentException("Please insert a valid value");

        var entity = personRepository.findById(id)
                .orElseThrow(() -> new NullPointerException("no records found for this id"));

        PersonVO personVO = DozerMapper.parseObject(entity, PersonVO.class);
        return personVO;
    }

    public List<PersonVO> findAll() {
        var people = personRepository.findAll();
        return DozerMapper.parseListObjects(people, PersonVO.class);
    }

    public PersonVO createPerson(PersonVO vo) {
        if (vo.getName() == null || vo.getDateOfBirth() == null) throw new NullPointerException("Please insert a valid value");
        if (vo.getName().isBlank() || vo.getDateOfBirth().isBlank()) throw new IllegalArgumentException("Please insert a valid value");

        vo.setDateOfBirth(formattedDate(vo.getDateOfBirth()));
        personRepository.save(DozerMapper.parseObject(vo, Person.class));
        return vo;
    }

    public PersonVO updatePersonById(Long id, PersonVO vo) {
        if (id == null) throw new NullPointerException("Please insert a valid value");
        if (vo.getName() == null || vo.getDateOfBirth() == null) throw new NullPointerException("Please insert a valid value");
        if (vo.getName().isBlank() || vo.getDateOfBirth().isBlank()) throw new IllegalArgumentException("Please insert a valid value");


        var entity = personRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no records found for this id"));

        entity.setName(vo.getName());
        entity.setDateOfBirth(formattedDate(vo.getDateOfBirth()));

        if (vo.getAddress() != null) entity.setAddress(vo.getAddress());

        personRepository.save(entity);
        return vo;
    }

    public AddressVO addAddressToPerson(Long id, AddressVO vo) {
        if (id == null) throw new NullPointerException("Please insert a valid value");
        if (
                vo.getCity() == null ||
                vo.getNumber() == null ||
                vo.getZipCode() == null ||
                vo.getCity() == null
        ) throw new NullPointerException("Please insert a valid value");

        if (
                vo.getCity().isBlank() ||
                vo.getNumber().toString().isBlank() ||
                vo.getZipCode().toString().isBlank() ||
                vo.getCity().isBlank()
        ) throw new IllegalArgumentException("Please insert a valid value");

        if (vo.getMainAddress() == null) {
            vo.setMainAddress(false);
        }

        var entity = personRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no records found for this id"));

        var addresses = entity.getAddress();
        addresses.add(DozerMapper.parseObject(vo, Address.class));
        entity.setAddress(addresses);
        personRepository.save(entity);
        return vo;
    }

    public List<AddressVO> getPersonAddresses(Long id) {
        var entity = personRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no records found for this id"));

        List<AddressVO> addresses = DozerMapper.parseListObjects(entity.getAddress(), AddressVO.class);

        return addresses;
    }

    public AddressVO getMainAddress(Long id) {
        var entity = personRepository.findById(id).orElseThrow(
                () -> new NullPointerException("no records found for this id"));

        AddressVO mainAddress = new AddressVO();

        for (Address address : entity.getAddress()) {
            if (address.getMainAddress().equals(true)) {
                mainAddress = DozerMapper.parseObject(address, AddressVO.class);
            }
        }
        return mainAddress;
    }

    private String formattedDate(String s) {
        Date dt = null;
        try {
            dt = new SimpleDateFormat("ddMMyyyy").parse(s);
            if (dt.getTime() > new Date().getTime()) throw new Exception("InvalidDateException");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //ToDo InvalidDateException

        String formattedDate = new SimpleDateFormat("dd-MM-yyyy").format(dt);
        return formattedDate;
    }

}
