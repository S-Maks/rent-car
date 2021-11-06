package com.car.rent.controller.car;

import com.car.rent.model.Car;
import com.car.rent.model.CarMake;
import com.car.rent.model.CarModel;
import com.car.rent.model.DTO.CarDTO;
import com.car.rent.model.DTO.NameDTO;
import com.car.rent.service.CarService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/add")
    public String getPageAddCar() {
        return "car/add";
    }

    @PostMapping("/add")
    public String getAddCarModel(@ModelAttribute CarDTO dto) {
        carService.addCar(dto);
        return "redirect:/car/cars";
    }

    @GetMapping(value = "/edit")
    public String editUser(@RequestParam(value = "id", required = true) Long id, Model model) {
        CarDTO dto = carService.getCarDTOById(id);
        model.addAttribute("car", dto);
        return "car/edit";
    }

    @PostMapping(value = "/edit")
    public String editUser(@ModelAttribute CarDTO dto, BindingResult errors, Model model) throws Exception {
        carService.editCar(dto);
        return "redirect:/car/cars";
    }
}
