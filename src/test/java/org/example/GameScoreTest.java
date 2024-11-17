package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.PlayerScore.*;
import static org.example.TestFixtures.*;

class GameScoreTest {

    private static Stream<Arguments> should_return_correct_score_when_playerA_scores() {
        return Stream.of(
                Arguments.of(new GameScore(ZERO, ZERO), new GameScore(FIFTEEN, ZERO)),
                Arguments.of(new GameScore(FIFTEEN, ZERO), new GameScore(THIRTY, ZERO)),
                Arguments.of(a30Vs40Score(), deuce()),
                Arguments.of(a40Vs40Score(), advantagePlayerAScore()),
                Arguments.of(advantagePlayerBScore(), deuce()),
                Arguments.of(advantagePlayerAScore(), aWinVs40Score()),
                Arguments.of(a40Vs30Score(), new GameScore(WIN, THIRTY))
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_return_correct_score_when_playerA_scores(GameScore before, GameScore expectedAfter) {
        assertThat(before.scorePointForPlayerA()).isEqualTo(expectedAfter);
    }


    private static Stream<Arguments> should_throw_exception_if_trying_to_score_when_there_is_a_winner() {
        return Stream.of(
                Arguments.of(aWinVs40Score()),
                Arguments.of(a40VsWinScore())
        );
    }

    @ParameterizedTest
    @MethodSource
    void should_throw_exception_if_trying_to_score_when_there_is_a_winner(GameScore aGameAlreadyWon) {
        // WHEN
        assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerA)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("cannot score when there is a winner");
    }
}
