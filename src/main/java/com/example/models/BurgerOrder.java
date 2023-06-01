package com.example.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class BurgerOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Instant placedAt;
    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @ManyToMany
    @JoinTable(name = "burger_order_burgers",
                joinColumns = @JoinColumn(name = "order_id"),
                inverseJoinColumns = @JoinColumn(name = "burger_id"))
    private List<Burger> burgers;

    @Enumerated(EnumType.STRING)
    private Status status;
    public enum Status { CREATED, IN_MODERATION, ACCEPTED, REJECTED, FINISHED }

    @Transactional
    public void addBurger(Burger burger) {
        burgers.add(burger);
    }
}
