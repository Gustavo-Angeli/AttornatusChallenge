package com.gusta.people.services;


import com.gusta.people.data.*;
import com.gusta.people.mapper.mocks.*;
import com.gusta.people.model.*;
import com.gusta.people.repositories.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
public class PersonServicesTest {

    private final MockPerson personInput = new MockPerson();
    private final MockAddress addressInput = new MockAddress();

    private Person entity = personInput.mockEntity();
    private Address address = addressInput.mockEntity();

    private PersonVO vo = personInput.mockVO();
    private AddressVO addressVO = addressInput.mockVO();

    private final Person persisted = entity;

    @InjectMocks
    private PersonServices service;
    @Mock
    private PersonRepository repository;

    @BeforeAll
    void setUp() {
        MockitoAnnotations.openMocks(this);
        entity = personInput.mockEntity();
        vo = personInput.mockVO();
    }
    @AfterEach
    void reset() {
        entity = personInput.mockEntity();
        vo = personInput.mockVO();
    }

    // FindPersonById Tests
    @Test
    void testFindPersonById_success() {
        when(repository.findById(vo.getId())).thenReturn(Optional.ofNullable(entity));

        assertNotNull(service.findPersonById(vo.getId()));
    }
    @Test
    void testFindPersonById_InvalidParams() {
        assertThrows(NullPointerException.class, () -> service.findPersonById(null));
        assertThrows(IllegalArgumentException.class, () -> service.findPersonById(Long.valueOf("")));
    }
    @Test
    void testFindPersonById_PersonNotFound() {
        when(repository.findById(1L)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.findPersonById(1L));
    }
    // FindPersonById

    // FindAll Tests
    @Test
    void findAll() {
        when(repository.findAll()).thenReturn(Arrays.asList(entity, entity));

        assertNotNull(service.findAll());
    }
    // FindAll

    // CreatePerson Tests
    @Test
    void testCreatePerson_InvalidParams() {
        vo.setName(null);
        vo.setDateOfBirth(null);
        assertThrows(NullPointerException.class, () -> service.createPerson(vo));

        vo.setName("");
        vo.setDateOfBirth("");
        assertThrows(IllegalArgumentException.class, () -> service.createPerson(vo));
    }
    @Test
    void testCreatePerson_Success() {
        PersonVO createdPerson = service.createPerson(vo);
        assertNotNull(createdPerson);
    }
    // CreatePerson

    // UpdatePerson Tests
    @Test
    void testUpdatePerson_InvalidParams() {
        vo.setName(null);
        vo.setDateOfBirth(null);
        assertThrows(NullPointerException.class, () -> service.updatePersonById(1L, vo));

        vo.setName("");
        vo.setDateOfBirth("");
        assertThrows(IllegalArgumentException.class, () -> service.updatePersonById(1L, vo));

        assertThrows(NullPointerException.class, () -> service.updatePersonById(null, vo));
    }
    @Test
    void testUpdatePerson_PersonNotFound() {
        when(repository.findById(1L)).thenReturn(null);

        assertThrows(NullPointerException.class, () -> service.updatePersonById(1L, vo));
    }
    @Test
    void testUpdatePerson_Success() throws Exception {
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(entity));

        PersonVO updatedPerson = service.updatePersonById(1L, vo);
        assertNotNull(updatedPerson);
    }
    // UpdatePerson

    // addAddressToPerson Tests

    @Test
    void testAddAddressToPerson_InvalidParams() {
        assertThrows(NullPointerException.class, () -> service.addAddressToPerson(null, addressVO));

        addressVO.setCity(null);
        addressVO.setNumber(null);
        addressVO.setZipCode(null);
        assertThrows(NullPointerException.class, () -> service.addAddressToPerson(1L, addressVO));

        addressVO.setCity("");
        addressVO.setZipCode("");
        assertThrows(NullPointerException.class, () -> service.addAddressToPerson(1L, addressVO));
    }
    @Test
    void testAddAddressToPerson_PersonNotFound() {
        assertThrows(NullPointerException.class, () -> service.addAddressToPerson(1L, addressVO));
    }
    @Test
    void testAddAddressToPerson_Success() {
        List<Address> addresses = new ArrayList<>();
        addresses.add(address);
        entity.setAddress(addresses);
        when(repository.findById(1L)).thenReturn(Optional.ofNullable(entity));
        assertNotNull(service.addAddressToPerson(1L, addressVO));
    }
    // addAddressToPerson

}
