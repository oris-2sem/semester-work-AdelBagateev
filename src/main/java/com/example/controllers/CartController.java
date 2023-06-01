package com.example.controllers;

import com.example.dto.BurgerDto;
import com.example.dto.BurgerOrderDto;
import com.example.dto.RegistrationRequest;
import com.example.models.Burger;
import com.example.models.BurgerOrder;
import com.example.models.User;
import com.example.services.AuthenticationService;
import com.example.services.BurgerOrderService;
import com.example.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.example.models.BurgerOrder.Status;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final UserService userService;
    private final BurgerOrderService burgerOrderService;
    private List<BurgerOrderDto> burgers;

    @GetMapping
    public String cart(Model model, Authentication authentication) {
        User user = userService.retrievePrincipal(authentication);

        List<BurgerOrder> orders = burgerOrderService.getUserOrders(user);
        this.burgers = new LinkedList<>();
        Integer totalPrice = 0;

        for (BurgerOrder burgerOrder : orders) {
            Status status = burgerOrder.getStatus();
            List<Burger> orderBurgers = burgerOrder.getBurgers();

            if (status.equals(Status.CREATED)) {
                this.burgers.add(new BurgerOrderDto(burgerOrder.getId(), orderBurgers));
            }

            for (Burger orderBurger : orderBurgers) {
                totalPrice += orderBurger.getPrice();
            }
        }

        model.addAttribute("burgerOrders", this.burgers);
        model.addAttribute("totalPrice", totalPrice);

        return "cart";
    }

    @PostMapping("/delete")
    public String deleteOrder(@RequestParam String burgerId, @RequestParam String orderId) {

        Long burgerIdToDelete = Long.parseLong(burgerId);
        Long parsedOrderId = Long.parseLong(orderId);
        burgerOrderService.deleteBurgerFromOrder(burgerIdToDelete, parsedOrderId);

        return "redirect:/cart";
    }

    @PostMapping("/pay")
    public String pay(@RequestParam Long price) {

        burgerOrderService.setOrdersStatusInModeration(this.burgers);

        return "redirect:/cart";
    }
}