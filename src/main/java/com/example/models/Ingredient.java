package com.example.models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@ToString(exclude = "burgers")
public class Ingredient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Enumerated(EnumType.STRING)
    private Type type;
    private Integer price;
    @ManyToMany(mappedBy = "ingredients")
    private List<Burger> burgers;
    public enum Type { WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE }
}
