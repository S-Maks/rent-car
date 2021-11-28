package com.car.rent.model.DTO;

import com.car.rent.model.enums.Status;
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

    private Status status;

    private Long clientId;

    private Long carId;
}
