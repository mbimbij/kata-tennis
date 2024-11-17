package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class IntegerBasedGameScoreTest {

    private static Stream<Arguments> should_increase_score_properly_when_scoring_for_playerA() {
        return Stream.of(
                Arguments.of(new IntegerBasedGameScore(0,0), new IntegerBasedGameScore(1,0)),
                Arguments.of(new IntegerBasedGameScore(1,0), new IntegerBasedGameScore(2,0)),
                Arguments.of(new IntegerBasedGameScore(2,0), new IntegerBasedGameScore(3,0)),
                Arguments.of(new IntegerBasedGameScore(3,0), new IntegerBasedGameScore(4,0))
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_increase_score_properly_when_scoring_for_playerA(IntegerBasedGameScore before, IntegerBasedGameScore expectedAfter) {
        assertThat(before.scorePointForPlayerA()).isEqualTo(expectedAfter);
    }

    private static Stream<Arguments> should_increase_score_properly_when_scoring_for_playerB() {
        return Stream.of(
                Arguments.of(new IntegerBasedGameScore(0,0), new IntegerBasedGameScore(0,1)),
                Arguments.of(new IntegerBasedGameScore(0,1), new IntegerBasedGameScore(0,2)),
                Arguments.of(new IntegerBasedGameScore(0,2), new IntegerBasedGameScore(0,3)),
                Arguments.of(new IntegerBasedGameScore(0,3), new IntegerBasedGameScore(0,4))
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_increase_score_properly_when_scoring_for_playerB(IntegerBasedGameScore before, IntegerBasedGameScore expectedAfter) {
        assertThat(before.scorePointForPlayerB()).isEqualTo(expectedAfter);
    }

    private static Stream<Arguments> should_format_score_properly() {
        return Stream.of(
                Arguments.of(new IntegerBasedGameScore(0,0), "Player A : 0 / Player B : 0"),
                Arguments.of(new IntegerBasedGameScore(0,1), "Player A : 0 / Player B : 15"),
                Arguments.of(new IntegerBasedGameScore(0,2), "Player A : 0 / Player B : 30"),
                Arguments.of(new IntegerBasedGameScore(0,3), "Player A : 0 / Player B : 40"),
                Arguments.of(new IntegerBasedGameScore(0,3), "Player A : 0 / Player B : 40"),
                Arguments.of(new IntegerBasedGameScore(0,4), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(1,1), "Player A : 15 / Player B : 15"),
                Arguments.of(new IntegerBasedGameScore(1,2), "Player A : 15 / Player B : 30"),
                Arguments.of(new IntegerBasedGameScore(1,3), "Player A : 15 / Player B : 40"),
                Arguments.of(new IntegerBasedGameScore(1,4), "Player B wins the game")
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_format_score_properly(IntegerBasedGameScore gameScore, String expectedFormattedScore) {
        assertThat(gameScore.format()).isEqualTo(expectedFormattedScore);
    }
}
