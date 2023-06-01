package com.example.configuration;

import com.example.filters.AuthFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http,
                                                       UserDetailsService userDetailsServiceImpl,
                                                       PasswordEncoder passwordEncoder) throws Exception {

        AuthenticationManagerBuilder builder = http.getSharedObject(AuthenticationManagerBuilder.class);

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder);
        authProvider.setUserDetailsService(userDetailsServiceImpl);

        builder.authenticationProvider(authProvider);

        return builder.build();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity, AuthFilter authFilter) throws Exception {
        return httpSecurity
                .csrf().disable()
                .cors().configurationSource(request -> new CorsConfiguration().applyPermitDefaultValues())
                .and()
                .formLogin()
                .loginPage("/enter")
                .and()
                .oauth2Login()
                .and()
                .authorizeRequests()
                .mvcMatchers("/enter").permitAll()
                .mvcMatchers("/error").permitAll()
                .mvcMatchers("/register").anonymous()
                .mvcMatchers("/js/*").permitAll()
                .mvcMatchers("/css/*").permitAll()
                .mvcMatchers("/moderation/**").hasRole("ADMIN")
                .mvcMatchers("/moderation").hasRole("ADMIN")
                .mvcMatchers("/v3/api-docs/").permitAll()
                .mvcMatchers("/oath2/authorization/google").permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(authFilter)
                .build();
    }

}
