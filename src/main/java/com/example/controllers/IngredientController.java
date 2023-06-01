package com.example.controllers;

import com.example.models.Ingredient;
import com.example.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ingredient")
@RequiredArgsConstructor
public class IngredientController {
    private final IngredientService ingredientService;

    @GetMapping("/{id}")
    public ResponseEntity<Ingredient> getIngredient(@PathVariable Long id) {
        return ResponseEntity.ok(ingredientService.getIngredientById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void postIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.save(ingredient);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteIngredient(@PathVariable Long id) {
        ingredientService.delete(id);
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateIngredient(@RequestBody Ingredient ingredient) {
        ingredientService.update(ingredient);
    }
}
