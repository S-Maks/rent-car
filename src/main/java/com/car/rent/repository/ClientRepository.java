package com.car.rent.repository;

import com.car.rent.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Long> {
    Optional<Client> findFirstByUsername(String name);
}
