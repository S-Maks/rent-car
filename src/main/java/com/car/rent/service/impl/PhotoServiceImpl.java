package com.car.rent.service.impl;

import com.car.rent.model.CarPhoto;
import com.car.rent.repository.CarPhotoRepository;
import com.car.rent.repository.CarRepository;
import com.car.rent.service.PhotoService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.UUID;

@Service
@AllArgsConstructor
public class PhotoServiceImpl implements PhotoService {
    private final CarPhotoRepository photoRepository;
    private final CarRepository carRepository;

    @Override
    public void save(Long id, MultipartFile file) {
        String path = UUID.randomUUID().toString();
        CarPhoto carPhoto = CarPhoto.builder()
                .carId(carRepository.findById(id).get())
                .path(path)
                .build();
        photoRepository.save(carPhoto);
        if (!file.isEmpty()) {
            File finalFile = createFile(path);
            if (!finalFile.getParentFile().exists()) {
                finalFile.getParentFile().mkdirs();
            }
            try {
                finalFile.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try (FileOutputStream fos = new FileOutputStream(finalFile)) {
                byte[] bytes = file.getBytes();
                fos.write(bytes);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    private File createFile(String uuid) {
        return new File("upload/" + uuid);
    }
}
