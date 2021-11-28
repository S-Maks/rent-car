package com.car.rent.service;

import com.car.rent.model.Contract;

import java.util.List;

public interface ContractService {
    void save(String start, String end, String carId);

    List<Contract> findAll();

    List<Contract> findByStatus(String status);

    void approved(Long id);

    void blocked(Long id);
}
