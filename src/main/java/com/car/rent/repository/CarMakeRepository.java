package com.car.rent.repository;

import com.car.rent.model.CarMakeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMakeRepository extends JpaRepository<CarMakeModel, Long> {
}
