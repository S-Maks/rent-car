package com.car.rent.controller.user;

import com.car.rent.model.DTO.ClientDTO;
import com.car.rent.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final ClientService clientService;

    @GetMapping("/personal-info")
    public String privateInfo(Model model) {
        ClientDTO dto = clientService.findPersonalInfo();
        model.addAttribute("client", dto);
        return "user/personal-info";
    }
}
