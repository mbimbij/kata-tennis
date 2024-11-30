package org.example.acceptance.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.rest.SpringTennisApplication;
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
        }
}
