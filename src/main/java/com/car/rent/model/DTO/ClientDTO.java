package com.car.rent.model.DTO;

import com.car.rent.model.Client;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClientDTO {
    private Long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String document;

    private String documentNumber;

    private String phone;

    private Integer experience;

    public static ClientDTO transferToDTO(Client client) {
        return ClientDTO.builder()
                .id(client.getId())
                .username(client.getUsername())
                .firstName(client.getFirstName())
                .lastName(client.getFirstName())
                .document(client.getDocument())
                .documentNumber(client.getDocumentNumber())
                .phone(client.getPhone())
                .experience(client.getExperience())
                .build();
    }
}
