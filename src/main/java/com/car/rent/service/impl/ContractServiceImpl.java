package com.car.rent.service.impl;

import com.car.rent.model.Car;
import com.car.rent.model.Client;
import com.car.rent.model.Contract;
import com.car.rent.model.enums.Status;
import com.car.rent.repository.CarRepository;
import com.car.rent.repository.ClientRepository;
import com.car.rent.repository.ContractRepository;
import com.car.rent.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

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
                .status(Status.REVIEW)
                .build();
        contractRepository.save(contract);
    }

    @Override
    public List<Contract> findAll() {
        return contractRepository.findAll();
    }

    @Override
    public List<Contract> findByStatus(String status) {
        if (status.equals("")) {
            return contractRepository.findAllByStatus(Status.REVIEW);
        } else {
            return contractRepository.findAllByStatus(Status.valueOf(status));
        }
    }

    @Override
    public List<Contract> findByUser() {
        Client client = clientRepository.findFirstByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get();
        return contractRepository.findAllByClientId(client);
    }

    @Override
    public void approved(Long id) {
        Contract contract = contractRepository.findById(id).get();
        contract.setStatus(Status.APPROVED);
        contractRepository.save(contract);
    }

    @Override
    public void blocked(Long id) {
        Contract contract = contractRepository.findById(id).get();
        contract.setStatus(Status.BLOCKED);
        contractRepository.save(contract);
    }

    @Override
    public void saveFile(Long id, HttpServletResponse response) {
        File file = new File("test.pdf");
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "filename=\"test.pdf\"");
        try {
            FileUtils.copyFile(file, response.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
