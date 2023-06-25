package com.sda.company.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@Profile(value = "with_security")
public class SecurityConfig {

    public static final String ROLE_USER = "USER";
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_POWER_USER = "POWER_USER";

    @Bean
    @Primary
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        manager.createUser(User
                .withUsername("user")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles(ROLE_USER)
                .build());

        manager.createUser(User
                .withUsername("admin")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles(ROLE_ADMIN, ROLE_USER)
                .build());

        manager.createUser(User
                .withUsername("power_user")
                .password(bCryptPasswordEncoder().encode("1234"))
                .roles(ROLE_POWER_USER, ROLE_ADMIN, ROLE_USER)
                .build());

        return manager;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> {
                    auth.requestMatchers("/api/v1/company/hello").hasRole(ROLE_USER);
                    auth.requestMatchers("/api/v1/company/create").hasRole(ROLE_ADMIN);
                    auth.requestMatchers("/api/v1/company/findAll").hasRole(ROLE_USER);
                    auth.requestMatchers("/api/v1/company/generate").hasRole(ROLE_POWER_USER);
                    auth.requestMatchers("/api/v1/company/findByName/{name}").hasRole(ROLE_ADMIN);
                    auth.requestMatchers("/api/v1/company/update").hasRole(ROLE_ADMIN);
                    auth.requestMatchers("/api/v1/company/delete/{id}").hasRole(ROLE_ADMIN);

                    auth.requestMatchers("/api/v1/employee/create").hasRole(ROLE_ADMIN);
                    auth.requestMatchers("/api/v1/employee/findAll").hasRole(ROLE_USER);
                    auth.requestMatchers("/api/v1/employee/findByName/{name}").hasRole(ROLE_ADMIN);
                    auth.requestMatchers("/api/v1/employee/generate").hasRole(ROLE_POWER_USER);

                    // for thymeleaf
                    auth.requestMatchers("/").hasRole(ROLE_USER);
                    auth.requestMatchers("/start").hasRole(ROLE_USER);
                    auth.requestMatchers("/company").hasRole(ROLE_USER);
                })
                .httpBasic(Customizer.withDefaults());

        return httpSecurity.build();
    }
}
