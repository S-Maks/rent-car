package com.car.rent.service.impl;

import com.car.rent.model.Car;
import com.car.rent.model.CarMake;
import com.car.rent.model.CarModel;
import com.car.rent.model.DTO.CarDTO;
import com.car.rent.model.DTO.NameDTO;
import com.car.rent.model.DTO.NewDTO;
import com.car.rent.repository.CarMakeRepository;
import com.car.rent.repository.CarModelRepository;
import com.car.rent.repository.CarPhotoRepository;
import com.car.rent.repository.CarRepository;
import com.car.rent.service.CarService;
import com.car.rent.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarMakeRepository carMakeRepository;
    private final CarModelRepository carModelRepository;
    private final CarRepository carRepository;
    private final CarPhotoRepository carPhotoRepository;
    private final PhotoService photoService;

    @Override
    @Transactional
    public List<CarDTO> getCarsGeneralInfoByParam(String param) {
        return carRepository
                .findAll().stream()
                .map(CarDTO::transferToDTO)
                .peek(data -> data.setPhoto(photoService.findByCarId(data.getId())))
                .filter(car -> car.getNameCarMake().toLowerCase().contains(param.toLowerCase())
                        || car.getNameCarModel().toLowerCase().contains(param.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CarMake> getCarsMakeGeneralInfoByParam(String param) {
        return carMakeRepository.findAll().stream()
                .filter(carMake -> carMake.getName().toLowerCase().contains(param.toLowerCase()))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public List<CarModel> getCarModels(Long id) {
        CarMake make = carMakeRepository.findById(id).get();
        return carModelRepository.findAllByMakeId(make);
    }

    @Override
    @Transactional
    public CarDTO getCarDTOById(Long id) {
        return CarDTO.transferToDTO(carRepository.findById(id).get());
    }

    @Override
    @Transactional
    public void addCar(CarDTO dto) {
        CarMake make = getOrCreateCarMake(dto.getNameCarMake());
        CarModel model = getOrCreateCarModel(dto.getNameCarModel(), make);
        Car car = Car.builder()
                .plateNumber(dto.getPlateNumber())
                .pricePerDay(dto.getPricePerDay())
                .transmission(dto.getTransmission())
                .airConditioner(dto.getAirConditioner())
                .body(dto.getBody())
                .seats(dto.getSeats())
                .productionYear(dto.getProductionYear())
                .class_auto(dto.getClass_auto())
                .engineCapacity(dto.getEngineCapacity())
                .engineType(dto.getEngineType())
                .consumption(dto.getConsumption())
                .carModel(model)
                .build();
        carRepository.save(car);
    }

    @Override
    @Transactional
    public void addCarMake(NameDTO name) {
        CarMake carMake = CarMake.builder()
                .name(name.getName())
                .build();
        carMakeRepository.save(carMake);
    }

    @Override
    @Transactional
    public void deleteCar(Long id) {
        carRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteCarMake(Long id) {
        carMakeRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void editCar(CarDTO dto) {
        CarMake make = getOrCreateCarMake(dto.getNameCarMake());
        CarModel model = getOrCreateCarModel(dto.getNameCarModel(), make);
        Car car = Car.builder()
                .id(dto.getId())
                .plateNumber(dto.getPlateNumber())
                .pricePerDay(dto.getPricePerDay())
                .transmission(dto.getTransmission())
                .airConditioner(dto.getAirConditioner())
                .body(dto.getBody())
                .seats(dto.getSeats())
                .productionYear(dto.getProductionYear())
                .class_auto(dto.getClass_auto())
                .engineCapacity(dto.getEngineCapacity())
                .engineType(dto.getEngineType())
                .consumption(dto.getConsumption())
                .carModel(model)
                .build();
        carRepository.save(car);
    }

    private CarMake getOrCreateCarMake(String name) {
        Optional<CarMake> carMake = carMakeRepository.findFirstByName(name.toUpperCase());
        if (carMake.isPresent()) {
            return carMake.get();
        } else {
            CarMake make = CarMake.builder()
                    .name(name.toUpperCase())
                    .build();
            return carMakeRepository.save(make);
        }
    }

    private CarModel getOrCreateCarModel(String modelName, CarMake make) {
        Optional<CarModel> carModel = carModelRepository.findFirstByName(modelName.toUpperCase());
        if (carModel.isPresent()) {
            return carModel.get();
        } else {
            CarModel model = CarModel.builder()
                    .name(modelName.toUpperCase())
                    .makeId(make)
                    .build();
            return carModelRepository.save(model);
        }
    }
}
