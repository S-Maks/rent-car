package com.car.rent.controller;

import com.car.rent.model.DTO.ClientDTO;
import com.car.rent.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MainController {

    private final ClientService clientService;
    private final BCryptPasswordEncoder encoder;

    @RequestMapping(value = "/")
    public String getMainPage(Authentication authentication) {
        if (authentication == null) {
            return "main/main";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/home")
    public String getHomePage() {
        return "home/home";
    }

    @GetMapping("/login")
    public String loginPage(Authentication authentication, ModelMap model, HttpServletRequest request) {
        if (request.getParameterMap().containsKey("error")) {
            model.addAttribute("error", true);
        }
        if (authentication == null) {
            return "main/login";
        } else {
            return "redirect:/home";
        }
    }

    @GetMapping("/registration")
    public String registrationPageGet() {
        return "main/registration";
    }

    @PostMapping("/registration")
    public String registrationPagePost(@ModelAttribute ClientDTO client) {
        clientService.registration(client);
        return "redirect:/home";
    }
}
