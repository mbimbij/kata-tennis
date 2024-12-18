package org.example.rest;

import org.example.shared.core.ScoreFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringTennisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringTennisApplication.class, args);
    }

    @Bean
    public ScoreFactory scoreFactory() {
        return ScoreFactory.getInstance();
    }
}
