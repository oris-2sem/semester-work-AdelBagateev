package com.example;

import com.example.models.Burger;
import com.example.models.BurgerOrder;
import com.example.models.Ingredient;
import com.example.models.User;
import com.example.repositories.BurgerOrderRepository;
import com.example.repositories.BurgerRepository;
import com.example.repositories.IngredientRepository;
import com.example.repositories.UserRepository;
import com.example.services.IngredientService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@SpringBootApplication
@RequiredArgsConstructor
public class BurgerStoreApplication {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    public static void main(String[] args) {
        SpringApplication.run(BurgerStoreApplication.class, args);
    }

}
