package com.car.rent.repository;

import com.car.rent.model.CarMake;
import com.car.rent.model.CarModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CarModelRepository extends JpaRepository<CarModel, Long> {
    List<CarModel> findAllByMakeId(CarMake makeId);

    Optional<CarModel> findFirstByName(String name);
}
