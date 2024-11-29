package org.example.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.restapi.GameScoreController;
import org.example.restapi.ScoreDto;
import org.example.restapi.SpringTennisApplication;
import org.example.core.Score;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.bean.override.mockito.MockitoSpyBean;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringTennisApplication.class
)
class SpringTennisApplicationShould {
    @LocalServerPort
    private int port;

    @MockitoSpyBean
    private GameScoreController gameScoreController;

    @BeforeEach
    void setUp() {
        RestAssured.port = port;
        RestAssured.urlEncodingEnabled = false;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    void should_return_empty_score() {
        Score score = new Score(1, 0);
        // GIVEN
        gameScoreController.setCurrentScore(score);

        // WHEN
        Response response = RestAssured.when()
                .get("/api/v1/game/score");
        
        // THEN status code is ok
        ScoreDto scoreDto = response.then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK)
                .extract()
                .as(ScoreDto.class);
        
        // AND
        ScoreDto expected = new ScoreDto("15", "0");
        assertThat(scoreDto).isEqualTo(expected);
    }
}
