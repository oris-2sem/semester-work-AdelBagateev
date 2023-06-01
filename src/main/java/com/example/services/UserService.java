package com.example.services;

import com.example.dto.RegistrationRequest;
import com.example.models.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserDetailsService userDetailsServiceImpl;
    private final AuthenticationService authenticationService;
    public User retrievePrincipal(Authentication authentication) {
        User user = null;

        try {
            user = (User) authentication.getPrincipal();
        } catch (Exception e) {
            OAuth2AuthenticationToken oath2Token = (OAuth2AuthenticationToken) authentication;
            if (oath2Token.getAuthorizedClientRegistrationId().equals("google")) {
                DefaultOidcUser defaultOidcUser = (DefaultOidcUser) oath2Token.getPrincipal();
                Map<String, Object> claims = defaultOidcUser.getClaims();
                String email = claims.get("email").toString();
                String name = claims.get("name").toString();

                try {
                    user = (User) userDetailsServiceImpl.loadUserByUsername(email);

                } catch (UsernameNotFoundException exception) {
                    user = authenticationService.register(
                            RegistrationRequest.builder()
                                    .email(email)
                                    .firstName(name)
                                    .password(UUID.randomUUID().toString())
                                    .build()

                    );
                }
            }
        }

        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }

        return user;
    }
}
