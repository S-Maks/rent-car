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
@Table(name = "address", schema = "public")
public class Address {
    @Id
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client clientId;

    private String city;

    private String street;

    private String building;

    private String apartment;
}
