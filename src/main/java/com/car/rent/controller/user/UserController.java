package com.car.rent.controller.user;

import com.car.rent.model.DTO.ClientDTO;
import com.car.rent.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final ClientService clientService;

    @GetMapping("/registration")
    public String registrationPageGet() {
        return "main/registration";
    }

    @PostMapping("/registration")
    public String registrationPagePost(@ModelAttribute ClientDTO client) {
        System.out.println(client);
        clientService.registration(client);
        return "redirect:/signIn";
    }
}
