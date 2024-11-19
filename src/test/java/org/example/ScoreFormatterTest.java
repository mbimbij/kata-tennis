package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreFormatterTest {

    @ParameterizedTest
    @CsvFileSource(files = "src/test/resources/score_formatting_test_cases.csv", useHeadersInDisplayName = true)
    void should_format_score_properly(int playerAScore, int playerBScore, String expected) {
        // GIVEN
        Score score = new Score(playerAScore, playerBScore);

        // WHEN
        String actual = ScoreFormatter.format(score);

        // THEN
        assertThat(actual).isEqualTo(expected);
    }
}
