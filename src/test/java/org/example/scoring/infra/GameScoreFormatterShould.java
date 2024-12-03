package org.example.shared.infra;

import org.example.shared.core.GameScore;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

class GameScoreFormatterShould {

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/score_formatting_test_cases.csv", useHeadersInDisplayName = true)
    void format_score_properly(int playerAScore, int playerBScore, String expected) {
        // GIVEN
        GameScore score = new GameScore(playerAScore, playerBScore);
        ConsoleScoreFormatter formatter = new ConsoleScoreFormatter();

        // WHEN
        String actual = formatter.format(score);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }
}
