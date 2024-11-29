package org.example.acceptance;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.countscorerestapi.GameScoreController;
import org.example.countscorerestapi.ScoreDto;
import org.example.countscorerestapi.SpringTennisApplication;
import org.example.shared.Score;
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
        // GIVEN
        Score score = new Score(0, 0);
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
        ScoreDto expected = new ScoreDto("0", "0");
        assertThat(scoreDto).isEqualTo(expected);
    }
}
