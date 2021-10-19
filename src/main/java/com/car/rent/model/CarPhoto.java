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
@Table(name = "car_photo", schema = "public")
public class CarPhoto {
    @Id
    @ManyToOne
    @JoinColumn(name = "car_id")
    private Car carId;

    private String path;
}
