package com.example.controllers;

import com.example.models.Burger;
import com.example.models.Comment;
import com.example.models.User;
import com.example.services.BurgerService;
import com.example.services.CommentService;
import com.example.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.Instant;

@Controller
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {
    private final CommentService commentService;
    private final BurgerService burgerService;
    private final UserService userService;

    @PostMapping
    public String postComment(@RequestParam String burgerId, @RequestParam String text, Authentication authentication) {
        User user = userService.retrievePrincipal(authentication);

        Long parseBurgerId = Long.parseLong(burgerId);
        Burger burger = burgerService.findBurger(parseBurgerId);

        commentService.save(
                Comment.builder()
                        .author(user)
                        .createdAt(Instant.now())
                        .text(text)
                        .burger(burger)
                        .build()
        );

        return "redirect:/burger/" + burgerId;
    }
}
