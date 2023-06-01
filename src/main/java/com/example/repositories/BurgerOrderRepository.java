package com.example.repositories;

import com.example.models.BurgerOrder;
import com.example.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BurgerOrderRepository extends JpaRepository<BurgerOrder, Long> {
    List<BurgerOrder> findAllByUserAndStatus(User user, BurgerOrder.Status status);

    @Modifying
    @Query("update BurgerOrder bo set bo.status = :status where bo.id = :id")
    int updateOrderSetStatus(@Param("status") BurgerOrder.Status status, @Param("id") Long id);
}
