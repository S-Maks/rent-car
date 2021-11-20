package com.car.rent.controller.car;

import com.car.rent.model.CarMake;
import com.car.rent.model.CarModel;
import com.car.rent.model.DTO.CarDTO;
import com.car.rent.model.DTO.ContractDTO;
import com.car.rent.model.DTO.NameDTO;
import com.car.rent.model.DTO.PhotoDTO;
import com.car.rent.service.CarService;
import com.car.rent.service.ContractService;
import com.car.rent.service.PhotoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/car")
@RequiredArgsConstructor
public class CarController {
    private final CarService carService;
    private final ContractService contractService;
    private final PhotoService photoService;

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

    @GetMapping("/rent")
    public String rentCar(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("carId", id);
        return "car/rent";
    }

    @PostMapping("/rent")
    public String rentCarPost(@RequestParam(value = "startDate") String startDate, @RequestParam(value = "endDate") String endDate, @RequestParam(value = "carId") String carId) {
        contractService.save(startDate, endDate, carId);
        return "redirect:/car/cars";
    }

    @GetMapping("/add-photo")
    public String addPhoto(@RequestParam(value = "id", required = false) Long id, Model model) {
        model.addAttribute("id", id);
        return "car/add-photo";
    }

    @PostMapping("/add-photo")
    public String addPhotoModel(@RequestParam(value = "id", required = false) Long id, @RequestParam("file") MultipartFile file) {
        photoService.save(id, file);
        return "redirect:/car/cars";
    }

    @RequestMapping(value = "/image/{id}")
    @ResponseBody
    public byte[] getImage(@PathVariable(value = "id") String imageName) throws IOException {
        File serverFile = new File("src/main/resources/static/img/upload/" + imageName);
        return Files.readAllBytes(serverFile.toPath());
    }
}
