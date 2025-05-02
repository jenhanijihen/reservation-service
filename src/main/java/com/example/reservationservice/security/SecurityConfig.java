package com.example.reservationservice.security;

import com.example.reservationservice.feignClient.LoggingJwtTokenFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private LoggingJwtTokenFilter loggingJwtTokenFilter;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/reservations/**").permitAll()
                        .anyRequest().authenticated())
                        .oauth2ResourceServer(oauth2 -> oauth2
                                .jwt()
                        );

        http.addFilterBefore(loggingJwtTokenFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
