package org.example.countscorerestapi;

import org.example.shared.ScoreFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringTennisApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringTennisApplication.class, args);
    }

    @Bean
    public ScoreFactory scoreFactory() {
        return new ScoreFactory();
    }
}
