package org.example.acceptance.cucumber.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.apache.http.HttpStatus;
import org.example.core.Score;
import org.example.core.ScoreFactory;
import org.example.restapi.GameScoreController;
import org.example.restapi.ScoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTestsSteps {
    @LocalServerPort
    private int port;

    @Autowired
    private GameScoreController controller;

    @Autowired
    private ScoreFactory factory;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.urlEncodingEnabled = false;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @When("i query the score")
    public void iQueryTheScore() {
    }

    @Then("i get the following response:")
    public void i_get_the_following_response(ScoreDto expectedScore) {
        Score score = new Score(1, 0);
        // GIVEN
        controller.setCurrentScore(score);

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

    @Given("the score is reset")
    public void theScoreIsReset() {
        controller.setCurrentScore(factory.loveAll());
    }
}
