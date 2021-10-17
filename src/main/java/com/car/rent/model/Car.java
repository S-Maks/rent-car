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
@Table(name = "car", schema = "public")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Access(AccessType.PROPERTY)
    private long id;

    private String plateNumber;

    private Integer pricePerDay;

    private String transmission;

    private Boolean airConditioner;

    private String body;

    private Integer seats;

    private Integer productionYear;

    @Column(name = "class")
    private String class_auto;

    private Double engineCapacity;

    private String engineType;

    private Double consumption;

    @ManyToOne
    @JoinColumn(name = "model_id")
    private CarModel carModel;
}
