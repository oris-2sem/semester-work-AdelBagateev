package com.example.repositories;

import com.example.models.Burger;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BurgerRepository extends JpaRepository<Burger, Long> {
    List<Burger> findAllByOrderByIdAsc();
}
