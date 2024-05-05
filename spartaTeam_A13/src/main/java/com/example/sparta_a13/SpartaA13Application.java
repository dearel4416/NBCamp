package com.example.sparta_a13;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class SpartaA13Application {

    public static void main(String[] args) {
        SpringApplication.run(SpartaA13Application.class, args);
    }

}
