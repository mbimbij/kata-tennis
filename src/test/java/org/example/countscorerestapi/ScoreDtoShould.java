package org.example.countscorerestapi;

import org.example.shared.Score;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class ScoreDtoShould {
    @ParameterizedTest
    @MethodSource
    void map_value_from_domain_correctly(Score score, ScoreDto expected) {
        // WHEN
        ScoreDto scoreDto = ScoreDto.fromDomain(score);

        // THEN status code is ok
        assertThat(scoreDto).isEqualTo(expected);
    }

    private static Stream<Arguments> map_value_from_domain_correctly() {
        Arguments[] arguments = {
                Arguments.of(new Score(0, 0), new ScoreDto("0", "0")),
                Arguments.of(new Score(1, 0), new ScoreDto("15", "0")),
                Arguments.of(new Score(2, 0), new ScoreDto("30", "0")),
                Arguments.of(new Score(3, 0), new ScoreDto("40", "0")),
                Arguments.of(new Score(4, 0), new ScoreDto("Game", "0")),

                Arguments.of(new Score(0, 1), new ScoreDto("0", "15")),
                Arguments.of(new Score(0, 2), new ScoreDto("0", "30")),
                Arguments.of(new Score(0, 3), new ScoreDto("0", "40")),
                Arguments.of(new Score(0, 4), new ScoreDto("0", "Game")),

                Arguments.of(new Score(1, 1), new ScoreDto("15", "15")),
                Arguments.of(new Score(3, 2), new ScoreDto("40", "30")),

                Arguments.of(new Score(3, 3), new ScoreDto("40", "40")),
                Arguments.of(new Score(4, 4), new ScoreDto("40", "40")),
                Arguments.of(new Score(5, 5), new ScoreDto("40", "40")),

                Arguments.of(new Score(4, 3), new ScoreDto("Ad", "40")),
                Arguments.of(new Score(5, 4), new ScoreDto("Ad", "40")),
                Arguments.of(new Score(6, 5), new ScoreDto("Ad", "40")),

                Arguments.of(new Score(3, 4), new ScoreDto("40", "Ad")),
                Arguments.of(new Score(4, 5), new ScoreDto("40", "Ad")),
                Arguments.of(new Score(5, 6), new ScoreDto("40", "Ad")),
        };
        return Stream.of(arguments);
    }
}
