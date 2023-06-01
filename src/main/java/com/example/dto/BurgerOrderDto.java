package com.example.dto;

import com.example.models.Burger;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BurgerOrderDto {
    private Long id;
    private List<Burger> burgers;
}
