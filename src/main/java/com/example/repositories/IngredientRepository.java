package com.example.repositories;

import com.example.models.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, Long> {
    List<Ingredient> findAllByOrderByIdAsc();

    @Query(value = "from Ingredient i where i.price > (select avg(price) from Ingredient )")
    List<Ingredient> findAllByPriceGreaterThanAvgPrice();
}
