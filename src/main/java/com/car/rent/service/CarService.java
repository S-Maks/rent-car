package com.car.rent.service;

import com.car.rent.model.CarMake;
import com.car.rent.model.CarModel;
import com.car.rent.model.DTO.CarDTO;
import com.car.rent.model.DTO.NameDTO;
import com.car.rent.model.DTO.NewDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> getCarsGeneralInfoByParam(String param);

    List<CarMake> getCarsMakeGeneralInfoByParam(String param);

    List<CarModel> getCarModels(Long id);

    void addCar(CarDTO newDTO);

    void addCarMake(NameDTO name);

    void deleteCar(Long id);

    void deleteCarMake(Long id);
}
