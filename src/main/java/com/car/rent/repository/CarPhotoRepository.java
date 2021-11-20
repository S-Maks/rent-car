package com.car.rent.repository;

import com.car.rent.model.Car;
import com.car.rent.model.CarPhoto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarPhotoRepository extends JpaRepository<CarPhoto, Long> {
    List<CarPhoto> findByCarId(Car id);
}
