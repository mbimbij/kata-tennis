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
                Arguments.of(new IntegerBasedGameScore(0, 0), new IntegerBasedGameScore(1, 0)),
                Arguments.of(new IntegerBasedGameScore(1, 0), new IntegerBasedGameScore(2, 0)),
                Arguments.of(new IntegerBasedGameScore(2, 0), new IntegerBasedGameScore(3, 0)),
                Arguments.of(new IntegerBasedGameScore(3, 0), new IntegerBasedGameScore(4, 0))
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_increase_score_properly_when_scoring_for_playerA(IntegerBasedGameScore before, IntegerBasedGameScore expectedAfter) {
        assertThat(before.scorePointForPlayerA()).isEqualTo(expectedAfter);
    }

    private static Stream<Arguments> should_increase_score_properly_when_scoring_for_playerB() {
        return Stream.of(
                Arguments.of(new IntegerBasedGameScore(0, 0), new IntegerBasedGameScore(0, 1)),
                Arguments.of(new IntegerBasedGameScore(0, 1), new IntegerBasedGameScore(0, 2)),
                Arguments.of(new IntegerBasedGameScore(0, 2), new IntegerBasedGameScore(0, 3)),
                Arguments.of(new IntegerBasedGameScore(0, 3), new IntegerBasedGameScore(0, 4))
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_increase_score_properly_when_scoring_for_playerB(IntegerBasedGameScore before, IntegerBasedGameScore expectedAfter) {
        assertThat(before.scorePointForPlayerB()).isEqualTo(expectedAfter);
    }

    private static Stream<Arguments> should_format_score_properly() {
        return Stream.of(
                Arguments.of(new IntegerBasedGameScore(0, 0), "Player A : 0 / Player B : 0"),
                Arguments.of(new IntegerBasedGameScore(0, 1), "Player A : 0 / Player B : 15"),
                Arguments.of(new IntegerBasedGameScore(0, 2), "Player A : 0 / Player B : 30"),
                Arguments.of(new IntegerBasedGameScore(0, 3), "Player A : 0 / Player B : 40"),
                Arguments.of(new IntegerBasedGameScore(0, 3), "Player A : 0 / Player B : 40"),
                Arguments.of(new IntegerBasedGameScore(1, 1), "Player A : 15 / Player B : 15"),
                Arguments.of(new IntegerBasedGameScore(1, 2), "Player A : 15 / Player B : 30"),
                Arguments.of(new IntegerBasedGameScore(1, 3), "Player A : 15 / Player B : 40"),
                Arguments.of(new IntegerBasedGameScore(2, 1), "Player A : 30 / Player B : 15"),
                Arguments.of(new IntegerBasedGameScore(2, 2), "Player A : 30 / Player B : 30"),
                Arguments.of(new IntegerBasedGameScore(2, 3), "Player A : 30 / Player B : 40"),
                Arguments.of(new IntegerBasedGameScore(3, 1), "Player A : 40 / Player B : 15"),
                Arguments.of(new IntegerBasedGameScore(3, 2), "Player A : 40 / Player B : 30"),
                Arguments.of(new IntegerBasedGameScore(3, 3), "Deuce"),
                Arguments.of(new IntegerBasedGameScore(4, 4), "Deuce"),
                Arguments.of(new IntegerBasedGameScore(5, 5), "Deuce"),
                Arguments.of(new IntegerBasedGameScore(6, 6), "Deuce"),
                Arguments.of(new IntegerBasedGameScore(4, 3), "Advantage Player A"),
                Arguments.of(new IntegerBasedGameScore(5, 4), "Advantage Player A"),
                Arguments.of(new IntegerBasedGameScore(6, 5), "Advantage Player A"),
                Arguments.of(new IntegerBasedGameScore(7, 6), "Advantage Player A"),
                Arguments.of(new IntegerBasedGameScore(3, 4), "Advantage Player B"),
                Arguments.of(new IntegerBasedGameScore(4, 5), "Advantage Player B"),
                Arguments.of(new IntegerBasedGameScore(5, 6), "Advantage Player B"),
                Arguments.of(new IntegerBasedGameScore(6, 7), "Advantage Player B"),
                Arguments.of(new IntegerBasedGameScore(4, 2), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(4, 0), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(4, 1), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(5, 2), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(6, 3), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(7, 4), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(5, 3), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(6, 4), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(7, 5), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(8, 6), "Player A wins the game"),
                Arguments.of(new IntegerBasedGameScore(2, 4), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(0, 4), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(1, 4), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(2, 5), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(3, 6), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(4, 7), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(3, 5), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(4, 6), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(5, 7), "Player B wins the game"),
                Arguments.of(new IntegerBasedGameScore(6, 8), "Player B wins the game")
                );
    }

    @ParameterizedTest
    @MethodSource
    void should_format_score_properly(IntegerBasedGameScore gameScore, String expectedFormattedScore) {
        assertThat(gameScore.format()).isEqualTo(expectedFormattedScore);
    }
}
