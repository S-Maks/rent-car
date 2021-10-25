package com.car.rent.service.impl;

import com.car.rent.model.Client;
import com.car.rent.model.DTO.ManagerDTO;
import com.car.rent.model.Manager;
import com.car.rent.repository.ClientRepository;
import com.car.rent.repository.ManagerRepository;
import com.car.rent.repository.RoleRepository;
import com.car.rent.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {
    private final ClientRepository clientRepository;
    private final ManagerRepository managerRepository;
    private final BCryptPasswordEncoder encoder;
    private final RoleRepository roleRepository;

    @Override
    public List<Client> getUsersGeneralInfoByParam(String param) {
        return clientRepository
                .findAll().stream()
                .filter(user -> user.getFirstName().contains(param)
                        || user.getLastName().contains(param)
                        || user.getUsername().contains(param))
                .collect(Collectors.toList());
    }

    @Override
    public List<Manager> getManagersGeneralInfoByParam(String param) {
        return managerRepository
                .findAll().stream()
                .filter(user -> user.getUsername().contains(param))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteUser(Long id) {
        clientRepository.deleteById(id);
    }

    @Override
    public void saveManager(ManagerDTO managerDTO) {
        Manager manager = Manager.builder()
                .username(managerDTO.getUsername())
                .password(encoder.encode(managerDTO.getPassword()))
                .roleId(roleRepository.findByName("ROLE_MANAGER").get())
                .build();
        managerRepository.save(manager);
    }

    @Override
    public void deleteManager(Long id) {
        managerRepository.deleteById(id);
    }
}
