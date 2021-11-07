package com.car.rent.controller.car;

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

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;

    @GetMapping("/cars")
    public String getCarts(
            @RequestParam(value = "search", required = false, defaultValue = "") String name,
            @RequestParam(value = "sort", required = false, defaultValue = "") String sort,
            @RequestParam(value = "fromYear", required = false, defaultValue = "") String fromYear,
            @RequestParam(value = "toYear", required = false, defaultValue = "") String toYear,
            @RequestParam(value = "fuel", required = false, defaultValue = "") String fuel,
            @RequestParam(value = "auto", required = false, defaultValue = "") String auto,
            Model model) {
        List<CarDTO> carModelList = sortList(carService.getCarsGeneralInfoByParam(name), sort);
        carModelList = filterList(carModelList, fromYear, toYear, fuel, auto);
        model.addAttribute("result", carModelList);
        model.addAttribute("search", name);
        return "car/cars";
    }

    private List<CarDTO> sortList(List<CarDTO> carModelList, String sort) {
        switch (sort) {
            case "":
            case "none": {
                return carModelList;
            }
            case "1": {
                carModelList.sort(Comparator.comparingInt(CarDTO::getProductionYear).reversed());
                break;
            }
            case "2": {
                carModelList.sort(Comparator.comparingInt(CarDTO::getProductionYear));
                break;
            }
            case "3": {
                carModelList.sort(Comparator.comparing(CarDTO::getNameCarModel));
                break;
            }
            case "4": {
                carModelList.sort(Comparator.comparing(CarDTO::getNameCarModel).reversed());
                break;
            }
        }
        return carModelList;
    }

    private List<CarDTO> filterList(List<CarDTO> carModelList,
                                    String fromYear,
                                    String toYear,
                                    String fuel,
                                    String auto) {
        if (!"".equals(fromYear)) {
            carModelList = carModelList.stream()
                    .filter(entry -> entry.getProductionYear() >= Integer.parseInt(fromYear))
                    .collect(Collectors.toList());
        }
        if (!"".equals(toYear)) {
            carModelList = carModelList.stream()
                    .filter(entry -> entry.getProductionYear() <= Integer.parseInt(toYear))
                    .collect(Collectors.toList());
        }

        if (!"".equals(fuel)) {
            carModelList = carModelList.stream()
                    .filter(entry -> entry.getEngineType().equalsIgnoreCase(fuel))
                    .collect(Collectors.toList());
        }

        if (!"".equals(auto)) {
            carModelList = carModelList.stream()
                    .filter(entry -> entry.getClass_auto().equalsIgnoreCase(auto))
                    .collect(Collectors.toList());
        }

        return carModelList;
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
