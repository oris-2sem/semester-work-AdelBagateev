package com.example.controllers;

import com.example.models.BurgerOrder;
import com.example.services.BurgerOrderService;
import com.example.services.BurgerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/moderation")
@RequiredArgsConstructor
public class ModerationController {
    private final BurgerOrderService burgerOrderService;
    private final BurgerService burgerService;
    @GetMapping("/orders")
    public String orders(Model model) {
        List<BurgerOrder> burgerOrders = burgerOrderService.getAllBurgerOrdersByStatus(BurgerOrder.Status.IN_MODERATION);
        model.addAttribute("orders", burgerOrders);

        return "admin-panel";
    }

    @PostMapping
    public String proceedOrders(@RequestParam Map<String, String> params) {

        Long orderId = Long.parseLong(params.get("orderId"));

        if (params.get("accepted") != null) {
            Long acceptedBurgerId = Long.parseLong(params.get("accepted"));
            burgerService.updateBurgerStatus(acceptedBurgerId);

            burgerOrderService.setOrderStatus(BurgerOrder.Status.ACCEPTED, orderId);

        } else if (params.get("rejected") != null) {
            Long rejectedBurgerId = Long.parseLong(params.get("rejected"));
            burgerOrderService.deleteBurgerFromOrder(rejectedBurgerId, orderId);
        }

        return "redirect:/moderation/orders";
    }
}
