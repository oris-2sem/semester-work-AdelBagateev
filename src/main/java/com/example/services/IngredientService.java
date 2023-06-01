package com.example.services;

import com.example.models.Ingredient;
import com.example.repositories.IngredientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class IngredientService {
    private final IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAllByOrderByIdAsc();
    }

    public Map<String, List<Ingredient>> getIngredientsGroupedBy() {
       return ingredientRepository.findAllByOrderByIdAsc().stream()
                .collect(Collectors.groupingBy(ingredient -> ingredient.getType().name()));
    }

    public Ingredient getIngredientById(Long id) {
       return ingredientRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public void save(Ingredient ingredient) {
        ingredientRepository.save(ingredient);
    }

    public void delete(Long id) {
        ingredientRepository.deleteById(id);
    }

    public void update(Ingredient ingredient) {
        ingredientRepository.saveAndFlush(ingredient);
    }
}
