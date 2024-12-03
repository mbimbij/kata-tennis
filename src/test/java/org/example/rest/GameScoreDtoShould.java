package org.example.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.example.scoring.core.GameScore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.skyscreamer.jsonassert.JSONAssert;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class GameScoreDtoShould {
    @ParameterizedTest
    @MethodSource
    void map_value_from_domain_correctly(GameScore score, ScoreDto expected) {
        // WHEN
        ScoreDto scoreDto = ScoreDto.fromDomain(score);

        // THEN status code is ok
        assertThat(scoreDto).isEqualTo(expected);
    }

    @SneakyThrows
    @Test
    void be_serialized_properly_by_jackson() {
        // GIVEN
        ObjectMapper objectMapper = new ObjectMapper();
        ScoreDto scoreDto = new ScoreDto("15", "0");

        // WHEN
        String serializedValue = objectMapper.writeValueAsString(scoreDto);

        // THEN
        JSONAssert.assertEquals("""
                        {
                          "A": "15",
                          "B": "0"
                        }
                        """,
                serializedValue, true);
    }

    private static Stream<Arguments> map_value_from_domain_correctly() {
        Arguments[] arguments = {
                Arguments.of(new GameScore(0, 0), new ScoreDto("0", "0")),
                Arguments.of(new GameScore(1, 0), new ScoreDto("15", "0")),
                Arguments.of(new GameScore(2, 0), new ScoreDto("30", "0")),
                Arguments.of(new GameScore(3, 0), new ScoreDto("40", "0")),
                Arguments.of(new GameScore(4, 0), new ScoreDto("Game", "0")),
                Arguments.of(new GameScore(0, 1), new ScoreDto("0", "15")),
                Arguments.of(new GameScore(0, 2), new ScoreDto("0", "30")),
                Arguments.of(new GameScore(0, 3), new ScoreDto("0", "40")),
                Arguments.of(new GameScore(0, 4), new ScoreDto("0", "Game")),
                Arguments.of(new GameScore(1, 1), new ScoreDto("15", "15")),
                Arguments.of(new GameScore(3, 2), new ScoreDto("40", "30")),
                Arguments.of(new GameScore(3, 3), new ScoreDto("40", "40")),
                Arguments.of(new GameScore(4, 3), new ScoreDto("Ad", "40")),
                Arguments.of(new GameScore(3, 4), new ScoreDto("40", "Ad")),
                Arguments.of(new GameScore(5, 3), new ScoreDto("Game", "40")),
                Arguments.of(new GameScore(3, 5), new ScoreDto("40", "Game")),
        };
        return Stream.of(arguments);
    }
}
