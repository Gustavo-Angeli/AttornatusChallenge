package com.gusta.people.repositories;

import com.gusta.people.model.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.*;
import org.springframework.stereotype.*;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    @Query("SELECT a FROM Address a WHERE a.street =:street")
    Address findByName(@Param("street") String street);

    @Query("SELECT a.street FROM Address a WHERE a.street =:street")
    Boolean checkIfExists(@Param("street") String street);
}
