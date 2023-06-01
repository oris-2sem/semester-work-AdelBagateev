package com.example.controllers;


import com.example.models.Burger;
import com.example.models.BurgerOrder;
import com.example.models.Ingredient;
import com.example.models.User;
import com.example.services.BurgerOrderService;
import com.example.services.BurgerService;
import com.example.services.IngredientService;
import com.example.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;
import java.time.Instant;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final BurgerService burgerService;
    private final IngredientService ingredientService;
    private final BurgerOrderService burgerOrderService;
    private final UserService userService;

    @GetMapping("/self")
    public String self(Model model) {
        Map<String, List<Ingredient>> map = ingredientService.getIngredientsGroupedBy();

        model.addAttribute("wrappers", map.get("WRAP"));
        model.addAttribute("proteins", map.get("PROTEIN"));
        model.addAttribute("sauces", map.get("SAUCE"));
        model.addAttribute("veggies", map.get("VEGGIES"));
        model.addAttribute("cheeses", map.get("CHEESE"));

        return "order-self";
    }

    @PostMapping("/self")
    @Transactional
    public void processSelfOrder(@RequestParam("veggies") List<Long> veggies,
                                 @RequestParam("cheese") List<Long> cheeses,
                                 @RequestParam("sauce") List<Long> sauces,
                                 @RequestParam("protein") List<Long> proteins,
                                 @RequestParam("wrapper") Long wrapper,
                                 @RequestParam("total") String total,
                                 @RequestParam("burgerName") String burgerName,
                                 HttpServletResponse servletResponse,
                                 Authentication authentication) throws IOException {

        User user = userService.retrievePrincipal(authentication);

        List<Long> list = new LinkedList<>();
        list.addAll(veggies);
        list.addAll(cheeses);
        list.addAll(sauces);
        list.addAll(proteins);
        list.add(wrapper);

        List<Ingredient> ingredients = list.stream().map(ingredientService::getIngredientById).toList();

        Burger burger = Burger.builder()
                .name(burgerName)
                .author(user)
                .createdAt(Instant.now())
                .ingredients(ingredients)
                .price(Integer.parseInt(total))
                .type("SELF")
                .build();

        burger = burgerService.save(burger);

        BurgerOrder burgerOrder = BurgerOrder.builder()
                .status(BurgerOrder.Status.CREATED)
                .placedAt(Instant.now())
                .user(user)
                .burgers(List.of(burger))
                .build();

        burgerOrderService.save(burgerOrder);

        servletResponse.sendRedirect("/order/self");
    }

    @GetMapping("/select")
    public String select(Model model) {
        model.addAttribute("burgers", burgerService.findAllAcceptedBurgerDtos());

        return "order-select";
    }

    @PostMapping("/select/{id}")
    public String select(@PathVariable Long id, Authentication authentication) {
        Burger burger = burgerService.findBurger(id);
        User user = (User) authentication.getPrincipal();

        BurgerOrder burgerOrder = BurgerOrder.builder()
                .status(BurgerOrder.Status.CREATED)
                .placedAt(Instant.now())
                .user(user)
                .burgers(List.of(burger))
                .build();

        burgerOrderService.save(burgerOrder);

        return "redirect:/order/select";
    }

}
