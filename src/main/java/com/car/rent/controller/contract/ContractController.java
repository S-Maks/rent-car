package com.car.rent.controller.contract;

import com.car.rent.model.Client;
import com.car.rent.model.Contract;
import com.car.rent.model.enums.Status;
import com.car.rent.service.ContractService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/contract")
@AllArgsConstructor
public class ContractController {
    private final ContractService contractService;

    @GetMapping("/show")
    public String showUsers(@RequestParam(value = "search", required = false, defaultValue = "") String name,
                            @RequestParam(value = "status", required = false, defaultValue = "") String status,
                            Model model) {
        List<Contract> list = contractService.findByStatus(status);
        model.addAttribute("result", list);
        return "contract/show";
    }

    @GetMapping("/approved")
    public String approved(@RequestParam(value = "id", required = false) Long id, Model model) {
        contractService.approved(id);
        return "redirect:/contract/show";
    }

    @GetMapping("/blocked")
    public String blocked(@RequestParam(value = "id", required = false) Long id, Model model) {
        contractService.blocked(id);
        return "redirect:/contract/show";
    }

    @GetMapping("/save")
    public String saveFile(@RequestParam(value = "id", required = false) Long id, Model model, HttpServletResponse response) {
        contractService.saveFile(id, response);
        return "redirect:/contract/show";
    }
}
