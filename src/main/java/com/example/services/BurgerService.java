package com.example.services;

import com.example.dto.BurgerDto;
import com.example.models.Burger;
import com.example.models.BurgerOrder;
import com.example.models.Ingredient;
import com.example.repositories.BurgerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BurgerService {
    private final BurgerRepository burgerRepository;

    public List<BurgerDto> findAllAcceptedBurgerDtos() {
        List<Burger> burgers = burgerRepository.findAllByOrderByIdAsc();
        return burgers.stream()
                .filter(this::hasBurgerPassedModeration)
                .map(burger -> BurgerDto.builder()
                        .id(burger.getId())
                        .name(burger.getName())
                        .price(calculateBurgerPrice(burger))
                        .build()).toList();
    }

    @Transactional
    public void updateBurgerStatus(Long id) {
        Burger burger = burgerRepository.findById(id).orElseThrow();
        burger.setType("SELECT");
    }

    public List<Burger> findAllBurgers () {
        return burgerRepository.findAll();
    }

    public BurgerDto findBurgerDto(Long id) {
        Burger burger = burgerRepository.findById(id).orElseThrow();
        return BurgerDto.builder()
                .id(burger.getId())
                .name(burger.getName())
                .price(calculateBurgerPrice(burger))
                .build();
    }

    public Burger findBurger(Long id) {
        return burgerRepository.findById(id).orElseThrow(IllegalArgumentException::new);
    }

    public Burger save(Burger burger) {
        return burgerRepository.save(burger);
    }

    private Integer calculateBurgerPrice(Burger burger) {
        List<Ingredient> ingredients = burger.getIngredients();
        Integer result = 0;

        for (Ingredient ingredient : ingredients) {
            result += ingredient.getPrice();
        }

        return result;
    }
    private boolean hasBurgerPassedModeration(Burger burger) {
        List<BurgerOrder> orders = burger.getOrders();

        for (BurgerOrder order : orders) {
            if (order.getStatus().equals(BurgerOrder.Status.ACCEPTED)
                    || order.getStatus().equals(BurgerOrder.Status.FINISHED)) {
                return true;
            }
        }

        return false;
    }

}
