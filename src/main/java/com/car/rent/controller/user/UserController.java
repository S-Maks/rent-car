package com.car.rent.controller.user;

import com.car.rent.model.Contract;
import com.car.rent.model.DTO.ClientDTO;
import com.car.rent.service.ClientService;
import com.car.rent.service.ContractService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final ClientService clientService;
    private final ContractService contractService;

    @GetMapping("/personal-info")
    public String privateInfo(Model model) {
        ClientDTO dto = clientService.findPersonalInfo();
        model.addAttribute("client", dto);
        return "user/personal-info";
    }

    @GetMapping("/edit")
    public String getPagePersonalInfo(Model model) {
        ClientDTO dto = clientService.findPersonalInfo();
        model.addAttribute("client", dto);
        return "user/edit";
    }

    @PostMapping("/edit")
    public String editInfo(ClientDTO dto) {
        clientService.edit(dto);
        return "redirect:/user/personal-info";
    }

    @GetMapping("/contracts")
    public String getContracts(Model model) {
        List<Contract> contracts = contractService.findByUser();
        model.addAttribute("result", contracts);
        return "contract/show";
    }
}
