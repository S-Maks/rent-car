package com.car.rent.controller;

import com.car.rent.model.CarMakeModel;
import com.car.rent.model.CarModel;
import com.car.rent.repository.CarMakeRepository;
import com.car.rent.repository.CarModelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MainController {
    private final CarMakeRepository repository;
    private final CarModelRepository modelRepository;

    @RequestMapping(value = "/home")
    public String homePage() {
        System.out.println(repository.findAll());
        System.out.println(modelRepository.findAll());
        CarMakeModel model = repository.findById(1L).orElse(null);
        model.setName("BWM");
        repository.save(model);
        return "home/homePage";
    }
}
