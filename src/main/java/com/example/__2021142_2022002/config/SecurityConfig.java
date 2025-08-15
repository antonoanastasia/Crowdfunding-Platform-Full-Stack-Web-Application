package com.example.__2021142_2022002.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;   // <-- import
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityFilterChain http(HttpSecurity h) throws Exception {
        return h.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(a -> a
                        .requestMatchers("/v3/api-docs/**","/swagger-ui/**","/swagger-ui.html").permitAll()
                        .requestMatchers("/projects/**").permitAll()
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    PasswordEncoder encoder(){ return new BCryptPasswordEncoder(); }

    // <-- ΣΗΜΑΝΤΙΚΟ: επιστρέφει InMemoryUserDetailsManager
    @Bean
    InMemoryUserDetailsManager users(PasswordEncoder enc){
        var admin   = User.withUsername("admin").password(enc.encode("admin")).roles("ADMIN").build();
        var creator = User.withUsername("alice").password(enc.encode("creator")).roles("CREATOR").build();
        var backer  = User.withUsername("bob").password(enc.encode("backer")).roles("BACKER").build();
        return new InMemoryUserDetailsManager(admin, creator, backer);
    }
}