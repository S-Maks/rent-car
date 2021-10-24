package com.car.rent.repository;

import com.car.rent.model.Address;
import com.car.rent.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
