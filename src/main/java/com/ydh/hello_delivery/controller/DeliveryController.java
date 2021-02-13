package com.ydh.hello_delivery.controller;

import com.ydh.hello_delivery.entity.Delivery;
import com.ydh.hello_delivery.service.DeliveryService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
public class DeliveryController {

    private final DeliveryService deliveryService;

    @GetMapping("/all")
    public String home(Model model) {
        model.addAttribute("deliveries", deliveryService.findAll());
        return "/home";
    }

    @GetMapping("/")
    public String homeSearch (Model model, @RequestParam(required = false, defaultValue = "0") int page) {
        PaginationDto pagination = new PaginationDto(page);
        Page<Delivery> result = deliveryService.findAllWithPaging(pagination);

        model.addAttribute("deliveries", result.getContent());
        model.addAttribute("pageDto", new pageDto(result.getSize(), result.getNumber(), result.getTotalPages()));
        return "/deliveryView";
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

    @Data
    static class pageDto {
        private final int size;

        private final int currentPage;
        private final int lastPage;

        private final int startIndex;
        private final int endIndex;

        public pageDto(int size, int currentPage, int totalPage) {
            this.size = size;
            this.currentPage = currentPage;
            this.lastPage = totalPage - 1;

            if (currentPage <= 4) {
                this.startIndex = 0;
                this.endIndex = 8;
                return;
            }

            if (currentPage + 4 > lastPage) {
                this.startIndex = lastPage - 8;
                this.endIndex = lastPage;
                return;
            }

            this.startIndex = currentPage - 4;
            this.endIndex = currentPage + 4;
        }
    }
}
