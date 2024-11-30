package org.example.acceptance.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.acceptance.cucumber.utils.DtoMapper;
import org.example.rest.SpringTennisApplication;
import org.openapitools.client.api.GameScoreControllerApi;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {
                CucumberSpringConfiguration.Configuration.class,
                SpringTennisApplication.class,
        })
@EnableAutoConfiguration
@CucumberContextConfiguration
public class CucumberSpringConfiguration {
        @TestConfiguration
        public static class Configuration {
            @Bean
            public DtoMapper dtoMapper() {
                return new DtoMapper();
            }

            @Bean
            public GameScoreControllerApi gameScoreControllerApi() {
                return new GameScoreControllerApi();
            }
        }
}
