package com.car.rent.model.DTO;

import com.car.rent.model.Car;
import com.car.rent.repository.CarPhotoRepository;
import com.car.rent.service.PhotoService;
import com.car.rent.service.impl.PhotoServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CarDTO {

    private long id;

    private String plateNumber;//

    private Integer pricePerDay;//

    private String transmission;//

    private Boolean airConditioner;

    private String body;

    private Integer seats;//

    private Integer productionYear;//

    private String class_auto;//

    private Double engineCapacity;//

    private String engineType;//

    private Double consumption;//

    private String nameCarModel;//

    private String nameCarMake;//

    private String photo;//

    private Double experience;//

    public static CarDTO transferToDTO(Car car) {
        return CarDTO.builder()
                .id(car.getId())
                .plateNumber(car.getPlateNumber())
                .pricePerDay(car.getPricePerDay())
                .transmission(car.getTransmission())
                .airConditioner(car.getAirConditioner())
                .body(car.getBody())
                .seats(car.getSeats())
                .productionYear(car.getProductionYear())
                .class_auto(car.getClass_auto())
                .engineCapacity(car.getEngineCapacity())
                .engineType(car.getEngineType())
                .consumption(car.getConsumption())
                .nameCarModel(car.getCarModel().getName())
                .nameCarMake(car.getCarModel().getMakeId().getName())
                .experience(car.getExperience())
                .build();
    }
}
