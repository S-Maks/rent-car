package com.car.rent.service;

import com.car.rent.model.DTO.ClientDTO;

public interface ClientService {
    void registration(ClientDTO client);

    ClientDTO findPersonalInfo();

    void edit(ClientDTO client);
}
