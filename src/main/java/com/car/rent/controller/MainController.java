package com.car.rent.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequiredArgsConstructor
public class MainController {

    @RequestMapping(value = "/")
    public String homePage() {
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
}
