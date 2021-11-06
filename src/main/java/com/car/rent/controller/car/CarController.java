package com.car.rent.controller.car;

import com.car.rent.model.CarMake;
import com.car.rent.model.CarModel;
import com.car.rent.model.DTO.CarDTO;
import com.car.rent.model.DTO.NameDTO;
import com.car.rent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/cars")
    public String getCarts(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model) {
        List<CarDTO> carModelList = carService.getCarsGeneralInfoByParam(name);
        model.addAttribute("result", carModelList);
        model.addAttribute("search", name);
        return "car/cars";
    }

    @GetMapping("/deleteCar")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id, Model model) {
        carService.deleteCar(id);
        return "redirect:/car/cars";
    }

    @GetMapping("/car-make")
    public String getCarMake(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model) {
        List<CarMake> carMakes = carService.getCarsMakeGeneralInfoByParam(name);
        model.addAttribute("result", carMakes);
        model.addAttribute("search", name);
        return "car/car-make/car-make";
    }

    @GetMapping("/car-make/add")
    public String getAddCarPage() {
        return "car/car-make/add";
    }

    @PostMapping("/car-make/add")
    public String getAddCarMake(@ModelAttribute NameDTO name) {
        carService.addCarMake(name);
        return "redirect:/car/car-make";
    }

    @GetMapping("/car-make/delete")
    public String deleteNew(@RequestParam(value = "id", required = false) Long id, Model model) {
        carService.deleteCarMake(id);
        return "redirect:/car/car-make";
    }

    @GetMapping("/car-make/{id}/car-model")
    public String getModels(@PathVariable String id, Model model, @RequestParam(value = "search", required = false, defaultValue = "") String name) {
        List<CarModel> carModelList = carService.getCarModels(Long.parseLong(id));
        model.addAttribute("result", carModelList);
        model.addAttribute("id", id);
        return "car/car-model/models";
    }

    @GetMapping("/car-model/add")
    public String getAddCarModelPage(@RequestParam(value = "idMake", required = false, defaultValue = "") String idMake, Model model) {
        model.addAttribute("idMake", idMake);
        return "car/car-model/add";
    }

    @PostMapping("/add")
    public String getAddCarModel(@RequestParam(value = "id", required = false) String id,@ModelAttribute NameDTO name) {
        System.out.println(name);
        return "redirect:/home";
    }
}
