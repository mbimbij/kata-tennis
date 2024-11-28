package org.example.acceptance;

import io.restassured.RestAssured;
import org.apache.http.HttpStatus;
import org.example.countscorerestapi.SpringTennisApplication;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringTennisApplication.class
)
class SpringTennisApplicationShould {
    @LocalServerPort
    private int port;

    @Autowired
    WebApplicationContext wac;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.urlEncodingEnabled = false;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void should_return_empty_score() {
        RestAssured.when()
                .get("/api/v1/game/score")
                .then()
                .statusCode(HttpStatus.SC_OK);
    }
}
