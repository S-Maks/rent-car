package com.car.rent.service;

import com.car.rent.model.Client;
import com.car.rent.model.DTO.ManagerDTO;
import com.car.rent.model.Manager;

import java.util.List;

public interface ManagerService {
    List<Client> getUsersGeneralInfoByParam(String param);

    List<Manager> getManagersGeneralInfoByParam(String param);

    void deleteUser(Long id);

    void saveManager(ManagerDTO managerDTO);

    void deleteManager(Long id);
}
