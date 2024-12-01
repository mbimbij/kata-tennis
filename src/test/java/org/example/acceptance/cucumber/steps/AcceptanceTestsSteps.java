package org.example.acceptance.cucumber.steps;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSender;
import org.apache.http.HttpStatus;
import org.example.shared.core.GameScore;
import org.example.shared.core.GameScoreFactory;
import org.example.rest.GameScoreController;
import org.example.rest.ScoreDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.server.LocalServerPort;

import java.util.function.Function;

import static org.assertj.core.api.Assertions.assertThat;

public class AcceptanceTestsSteps {
    @LocalServerPort
    private int port;
    @Autowired
    private GameScoreController controller;
    @Autowired
    private GameScoreFactory factory;
    private ScoreDto scoreResponseDto;

    @Before
    public void setUp() {
        RestAssured.port = port;
        RestAssured.urlEncodingEnabled = false;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @When("i query the score")
    public void iQueryTheScore() {
        performApiAction(r -> r.get("/api/v1/game/score"));
    }

    @When("i score a point for player A")
    public void iScoreAPointForPlayerA() {
        performApiAction(r -> r.post("/api/v1/game/score/A"));
    }

    @When("i score a point for player B")
    public void iScoreAPointForPlayerB() {
        performApiAction(r -> r.post("/api/v1/game/score/B"));
    }

    @When("i score the following sequence: {string}")
    public void iScoreTheFollowingSequence(String sequence) {
        performApiAction(r -> r.post("/api/v1/game/score/sequence/%s".formatted(sequence)));
    }

    @Then("i get the following response:")
    public void i_get_the_following_response(ScoreDto expectedScore) {
        assertThat(scoreResponseDto).isEqualTo(expectedScore);
    }

    @Given("the score is reset")
    public void theScoreIsReset() {
        controller.setCurrentScore(factory.loveAll());
    }

    @Given("a score of {score}")
    public void aScoreOf(GameScore score) {
        controller.setCurrentScore(score);
    }

    @When("i reset the score")
    public void iResetTheScore() {
        performApiAction(r -> r.delete("/api/v1/game/score"));
    }

    private void performApiAction(Function<RequestSender, Response> action) {
        // WHEN
        Response response = action.apply(RestAssured.when());

        // THEN status code is ok
        ValidatableResponse validatableResponse = response.then()
                .contentType(ContentType.JSON)
                .statusCode(HttpStatus.SC_OK);

        // AND set the attribute for use in other steps
        scoreResponseDto = validatableResponse
                .extract()
                .as(ScoreDto.class);
    }
}
