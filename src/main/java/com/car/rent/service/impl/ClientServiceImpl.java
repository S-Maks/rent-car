package com.car.rent.service.impl;

import com.car.rent.model.Client;
import com.car.rent.model.DTO.ClientDTO;
import com.car.rent.repository.ClientRepository;
import com.car.rent.repository.RoleRepository;
import com.car.rent.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.beans.Transient;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder encoder;

    @Override
    public void registration(ClientDTO client) {
        Client clientModel = Client.builder()
                .username(client.getUsername())
                .password(encoder.encode(client.getPassword()))
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .document(client.getDocument())
                .documentNumber(client.getDocumentNumber())
                .phone(client.getPhone())
                .experience(client.getExperience())
                .roleId(roleRepository.findByName("ROLE_CLIENT").get())
                .build();
        clientRepository.save(clientModel);
    }

    @Override
    public ClientDTO findPersonalInfo() {
        return ClientDTO.transferToDTO(clientRepository.findFirstByUsername(SecurityContextHolder.getContext().getAuthentication().getName()).get());
    }

    @Override
    @Transactional
    public void edit(ClientDTO client) {
        Client model = clientRepository.findById(client.getId()).get();
        if (client.getPassword() != null) {
            model.setPassword(encoder.encode(client.getPassword()));
        }
        model.setFirstName(client.getFirstName());
        model.setLastName(client.getLastName());
        model.setDocument(client.getDocument());
        model.setDocumentNumber(client.getDocumentNumber());
        model.setPhone(client.getPhone());
        model.setExperience(client.getExperience());
        clientRepository.save(model);
    }
}
