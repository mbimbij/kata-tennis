package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

class IntegerBasedGameScoreFormattingTest {

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/integer_based_game_score_format_test_cases.csv", useHeadersInDisplayName = true)
    void should_format_score_properly(int playerAScore, int playerBScore, String expectedFormattedScore) {
        GameScore gameScore = new IntegerBasedGameScore(playerAScore, playerBScore);
        assertThat(gameScore.format()).isEqualTo(expectedFormattedScore);
    }
}
