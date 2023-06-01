package com.example.services;

import com.example.dto.BurgerOrderDto;
import com.example.models.Burger;
import com.example.models.BurgerOrder;
import com.example.models.User;
import com.example.repositories.BurgerOrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.LinkedList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BurgerOrderService {
    private final BurgerOrderRepository burgerOrderRepository;

    public BurgerOrder save(BurgerOrder burgerOrder) {
        return burgerOrderRepository.save(burgerOrder);
    }

    public List<BurgerOrder> getUserOrders(User user) {
        return burgerOrderRepository.findAllByUserAndStatus(user, BurgerOrder.Status.CREATED);
    }

    public List<BurgerOrder> getAllBurgerOrdersByStatus(BurgerOrder.Status status) {
        return burgerOrderRepository.findAll()
                .stream()
                .filter(burgerOrder -> burgerOrder.getStatus().equals(status))
                .toList();
    }

    @Transactional
    public void deleteBurgerFromOrder(Long burgerId, Long orderId) {
        BurgerOrder burgerOrder = burgerOrderRepository.findById(orderId).orElseThrow(IllegalAccessError::new);
        List<Burger> burgers = burgerOrder.getBurgers()
                .stream()
                .filter(burger -> !burger.getId().equals(burgerId))
                .toList();

        if (burgers.size() == 0) {
            burgerOrderRepository.deleteById(orderId);
            return;
        }

        burgerOrder.setBurgers(burgers);
    }

    @Transactional
    public void setOrderStatus(BurgerOrder.Status status, Long orderId) {
        BurgerOrder order = burgerOrderRepository.findById(orderId).orElseThrow();
        order.setStatus(status);
    }

    @Transactional
    public void setOrdersStatusInModeration(List<BurgerOrderDto> burgerOrderDtos) {
        for (BurgerOrderDto orderDto : burgerOrderDtos) {
            int count = burgerOrderRepository.updateOrderSetStatus(BurgerOrder.Status.IN_MODERATION, orderDto.getId());
            if (count == 0) {
                throw new IllegalArgumentException("There's no changes");
            }

        }
    }
}
