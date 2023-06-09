package com.dentalmoovi.webpage.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.dentalmoovi.webpage.services.UserDetailsCustom;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
            
            .authorizeHttpRequests(authorize -> authorize
                
                .requestMatchers("/public/**").permitAll()
                .requestMatchers("/admin/**").hasRole("USER")

                .anyRequest()
                .authenticated());

                http.cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable());

        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());

    return http.build();
    }

    @Bean
    UserDetailsCustom userDetailsService(){
        return new UserDetailsCustom();
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}

