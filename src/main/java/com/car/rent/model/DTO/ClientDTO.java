package com.car.rent.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class ClientDTO {
    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String document;

    private String documentNumber;

    private String phone;

    private Integer experience;
}
