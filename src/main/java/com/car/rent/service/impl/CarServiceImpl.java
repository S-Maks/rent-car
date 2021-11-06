package com.car.rent.service.impl;

import com.car.rent.model.CarMake;
import com.car.rent.model.CarModel;
import com.car.rent.model.DTO.CarDTO;
import com.car.rent.model.DTO.NameDTO;
import com.car.rent.model.DTO.NewDTO;
import com.car.rent.repository.CarMakeRepository;
import com.car.rent.repository.CarModelRepository;
import com.car.rent.repository.CarRepository;
import com.car.rent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarMakeRepository carMakeRepository;
    private final CarModelRepository carModelRepository;
    private final CarRepository carRepository;

    @Override
    public List<CarDTO> getCarsGeneralInfoByParam(String param) {
        return carRepository
                .findAll().stream()
                .map(CarDTO::transferToDTO)
                .filter(car -> car.getNameCarMake().toLowerCase().contains(param.toLowerCase())
                        || car.getNameCarModel().toLowerCase().contains(param.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarMake> getCarsMakeGeneralInfoByParam(String param) {
        return carMakeRepository.findAll().stream()
                .filter(carMake -> carMake.getName().toLowerCase().contains(param.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    public List<CarModel> getCarModels(Long id) {
        CarMake make = carMakeRepository.findById(id).get();
        return carModelRepository.findAllByMakeId(make);
    }

    @Override
    public void addCar(NewDTO newDTO) {

    }

    @Override
    public void addCarMake(NameDTO name) {
        CarMake carMake = CarMake.builder()
                .name(name.getName())
                .build();
        carMakeRepository.save(carMake);
    }

    @Override
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    public void deleteCarMake(Long id) {
        carMakeRepository.deleteById(id);
    }
}
