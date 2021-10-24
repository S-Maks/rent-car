package com.car.rent.repository;

import com.car.rent.model.CarPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarPhotoRepository extends JpaRepository<CarPhoto, Long> {
}
