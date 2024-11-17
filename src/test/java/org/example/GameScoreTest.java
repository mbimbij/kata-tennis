package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.PlayerScore.THIRTY;
import static org.example.PlayerScore.WIN;
import static org.example.TestFixtures.*;

class GameScoreTest {

    private static Stream<Arguments> should_return_correct_score_when_playerA_scores() {
        return Stream.of(
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

    @Test
    void should_throw_exception_if_trying_to_score_when_there_is_a_winner() {
        // GIVEN
        GameScore aGameAlreadyWon = TestFixtures.aWinVs40Score();

        // WHEN
        assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerA)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("cannot score when there is a winner");
    }
}
