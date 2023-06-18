package com.sda.company.config;

import com.sda.company.util.CustomFakerCompany;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public CustomFakerCompany customFakerCompany() {
        return new CustomFakerCompany();
    }
}
