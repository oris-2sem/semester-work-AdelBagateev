package com.example.controllers;

import com.example.dto.RegistrationRequest;
import com.example.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationService authenticationService;

    @GetMapping("/enter")
    public String login() {
        return "login";
    }

    @GetMapping("/register")
    public String register() {
        return "registration";
    }

    @PostMapping(value = "/register")
    public void processRegister(@RequestParam Map<String, String> body, HttpServletResponse servletResponse)
            throws IOException {

        RegistrationRequest registrationRequest = RegistrationRequest
                .builder()
                .firstName(body.get("firstName"))
                .lastName(body.get("lastName"))
                .email(body.get("email"))
                .password(body.get("password"))
                .build();

        try {
            authenticationService.register(registrationRequest);
        } catch (Exception e) {
            servletResponse.sendRedirect("/register?error");
            return;
        }

        servletResponse.sendRedirect("/enter");
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }
}
