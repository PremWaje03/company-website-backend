package com.prem.company_website_backend.auth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(
            HttpSecurity http,
            JwtUtil jwtUtil) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 🔓 Auth APIs
                        .requestMatchers("/api/auth/**").permitAll()

                        // 🔓 Public APIs
                        .requestMatchers("/api/services/**").permitAll()
                        .requestMatchers("/api/technologies/**").permitAll()
                        .requestMatchers("/api/projects/**").permitAll()
                        .requestMatchers("/api/team/**").permitAll()
                        .requestMatchers("/api/testimonials/**").permitAll()
                        .requestMatchers("/api/company/**").permitAll()
                        .requestMatchers("/api/contact/**").permitAll()

                        // 🔐 ADMIN APIs (JWT REQUIRED)
                        .requestMatchers("/api/admin/**").authenticated()

                        // ❌ Anything else blocked
                        .anyRequest().denyAll()
                )
                .addFilterBefore(
                        new JwtFilter(jwtUtil),
                        UsernamePasswordAuthenticationFilter.class
                );

        return http.build();
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
