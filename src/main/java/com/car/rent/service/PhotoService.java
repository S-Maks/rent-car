package com.car.rent.service;

import org.springframework.web.multipart.MultipartFile;

public interface PhotoService {
    void save(Long id, MultipartFile file);

    String findByCarId(Long id);
}
