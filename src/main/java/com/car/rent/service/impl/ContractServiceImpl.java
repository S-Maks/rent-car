package com.car.rent.service.impl;

import com.car.rent.model.Car;
import com.car.rent.model.Client;
import com.car.rent.model.Contract;
import com.car.rent.repository.CarRepository;
import com.car.rent.repository.ClientRepository;
import com.car.rent.repository.ContractRepository;
import com.car.rent.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ContractServiceImpl implements ContractService {
    private final ContractRepository contractRepository;
    private final CarRepository carRepository;
    private final ClientRepository clientRepository;

    @Override
    public void save(String start, String end, String carId) {
        Car car = carRepository.findById(Long.parseLong(carId)).get();
        Client client = clientRepository.findFirstByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        Contract contract = Contract.builder()
                .startDate(LocalDateTime.parse(start))
                .endDate(LocalDateTime.parse(end))
                .carId(car)
                .clientId(client)
                .build();
        contractRepository.save(contract);
    }
}
