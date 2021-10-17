package com.car.rent.repository;

import com.car.rent.model.CarMake;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarMakeRepository extends JpaRepository<CarMake, Long> {
}
