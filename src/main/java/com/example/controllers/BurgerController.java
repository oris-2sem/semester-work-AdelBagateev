package com.example.controllers;

import com.example.models.Burger;
import com.example.services.BurgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.Instant;
import java.time.LocalDateTime;

@Controller
@RequestMapping("/burger")
@RequiredArgsConstructor
public class BurgerController {
    private final BurgerService burgerService;

    @GetMapping("/{id}")
    public String getBurger(@PathVariable Long id, Model model) {
        Burger burger = burgerService.findBurger(id);
        model.addAttribute("burger", burger);

        return "burger";
    }
}
