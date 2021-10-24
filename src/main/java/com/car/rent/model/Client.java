package com.car.rent.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "client", schema = "public")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    private long id;

    private String username;

    private String password;

    private String firstName;

    private String lastName;

    private String document;

    private String documentNumber;

    private String phone;

    private Integer experience;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private Role roleId;
}
