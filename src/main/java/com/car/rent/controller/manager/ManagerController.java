package com.car.rent.controller.manager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    @GetMapping("/registration")
    public String registrationPageGet() {
        return "main/registration";
    }

    @PostMapping("/registration")
    public String registrationPagePost() {
        return "redirect:/signIn";
    }
}
