package edu.erezd.erezproject.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
<<<<<<< HEAD
=======
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

>>>>>>> b9077c4 (V1 for Project)

@Configuration
public class AppConfig {

    @Bean
    ModelMapper getModelMapper() {

        return new ModelMapper();
    }
<<<<<<< HEAD
=======

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

>>>>>>> b9077c4 (V1 for Project)
}