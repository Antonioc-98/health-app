package com.app.HealthApp.healthapp.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http //Security pravila za aplikaciju
                .csrf(csrf -> csrf.disable()) // Isključen CSRF radi jednostavnosti
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers(
                            "/",
                            "/login",
                            "/register",
                            "/forgot-password",
                            "/reset-password",
                            "/password-reset", // Allow the combined reset page
                            "/request-reset-token", // Allow requesting tokens
                            "/reset-password",
                            "/css/**",
                            "/js/**",
                            "/images/**").permitAll(); //Dopusti pristup bez autentifikacije
                    auth.anyRequest().authenticated(); // Svi ostali requestovi traže autentifikaciju
                })
                .formLogin(form -> form //Custom login forma
                        .loginPage("/login")
                        .loginProcessingUrl("/login") //URL gdje je forma submitana
                        .defaultSuccessUrl("/", true)
                        .failureUrl("/login?error=true")
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout") //URL za logout triggering
                        .logoutSuccessUrl("/")
                        .permitAll()
                );

        return http.build(); //Finalizira i gradi SecurityFilterChain objekt
    }

    @Bean //Bean za hashanje lozinki
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
