package org.example.acceptance.cucumber;

import io.cucumber.spring.CucumberContextConfiguration;
import org.example.restapi.SpringTennisApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = {
                AcceptanceTestsSpringConfiguration.class,
                SpringTennisApplication.class,
        })
@EnableAutoConfiguration
@CucumberContextConfiguration
public class CucumberSpringConfiguration {
}
