package com.example.dto;

import lombok.*;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class RegistrationRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
