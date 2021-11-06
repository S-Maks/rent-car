package com.car.rent.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class NameDTO {

    private Integer id;
    private String name;
}
