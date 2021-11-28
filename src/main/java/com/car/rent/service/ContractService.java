package com.car.rent.service;

import com.car.rent.model.Contract;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ContractService {
    void save(String start, String end, String carId);

    List<Contract> findAll();

    List<Contract> findByStatus(String status);

    List<Contract> findByUser();

    void approved(Long id);

    void blocked(Long id);

    void saveFile(Long id, HttpServletResponse response);
}
