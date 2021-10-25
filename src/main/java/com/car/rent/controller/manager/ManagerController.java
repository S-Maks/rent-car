package com.car.rent.controller.manager;

import com.car.rent.model.Client;
import com.car.rent.model.DTO.ManagerDTO;
import com.car.rent.model.Manager;
import com.car.rent.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/manager")
@RequiredArgsConstructor
public class ManagerController {
    private final ManagerService managerService;

    @GetMapping("/registration")
    public String registrationPageGet() {
        return "manager/registration";
    }

    @PostMapping("/registration")
    public String registrationManager(@ModelAttribute ManagerDTO manager) {
        managerService.saveManager(manager);
        return "redirect:/manager/show-managers";
    }

    @GetMapping("/show-users")
    public String showUsers(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model) {
        List<Client> clientList = managerService.getUsersGeneralInfoByParam(name);
        model.addAttribute("result", clientList);
        model.addAttribute("search", name);
        return "manager/users";
    }

    @GetMapping("/deleteUser")
    public String deleteUser(@RequestParam(value = "id", required = false) Long id, Model model) {
        managerService.deleteUser(id);
        return "redirect:/manager/show-users";
    }

    @GetMapping("/show-managers")
    public String showManagers(@RequestParam(value = "search", required = false, defaultValue = "") String name, Model model) {
        List<Manager> managers = managerService.getManagersGeneralInfoByParam(name);
        model.addAttribute("result", managers);
        model.addAttribute("search", name);
        return "manager/managers";
    }

    @GetMapping("/deleteManager")
    public String deleteManager(@RequestParam(value = "id", required = false) Long id, Model model) {
        managerService.deleteManager(id);
        return "redirect:/manager/show-managers";
    }
}
