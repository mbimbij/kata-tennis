package org.example.rest;

import org.example.scoring.core.GameScoreFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringTennisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringTennisApplication.class, args);
    }

    @Bean
    public GameScoreFactory scoreFactory() {
        return GameScoreFactory.getInstance();
    }
}
