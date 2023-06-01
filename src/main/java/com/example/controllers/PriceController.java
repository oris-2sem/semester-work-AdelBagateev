package com.example.controllers;

import com.example.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PriceController {
    private final IngredientService ingredientService;
    @GetMapping
    public String prices(Model model) {
        model.addAttribute("ingredients", ingredientService.getAllIngredients());

        return "prices";
    }
}
