package com.example.petservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EnableJpaRepositories("com.example.petservice.repository")  // ✅ Репозитории
@EntityScan("com.example.petservice.model")  // ✅ Используем правильный пакет с сущностями
public class PetserviceApplication {
    public static void main(String[] args) {
        SpringApplication.run(PetserviceApplication.class, args);
    }
}


