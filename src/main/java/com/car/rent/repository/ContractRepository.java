package com.car.rent.repository;

import com.car.rent.model.Client;
import com.car.rent.model.Contract;
import com.car.rent.model.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContractRepository extends JpaRepository<Contract, Long> {
    List<Contract> findAllByStatusAndClientId(Status status, Client client);

    List<Contract> findAllByStatus(Status status);

    List<Contract> findAllByClientId(Client client);
}
