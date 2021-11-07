package com.car.rent.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@Builder
public class ContractDTO {
    private long id;

    private LocalDateTime startDate;

    private LocalDateTime endDate;

    private Long clientId;

    private Long carId;
}
