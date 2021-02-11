package com.ydh.hello_delivery.controller;

import com.ydh.hello_delivery.service.DeliveryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("deliveries", deliveryService.findAll());
        return "/home";
    }

    @PostMapping("/{id}/ship")
    public String ship(@PathVariable("id") Long id) {
        deliveryService.startDelivery(id);
        return "redirect:/";
    }

    @PostMapping("/{id}/comp")
    public String comp(@PathVariable("id") Long id) {
        deliveryService.endDelivery(id);
        return "redirect:/";
    }
}
