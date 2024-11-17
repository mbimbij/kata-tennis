package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.GameScore.loveAll;
import static org.example.GameScoreFixtures.*;

class GameScoreTest {

    @Nested
    class Computing {
        @Nested
        class ScoreForPlayerA {
            private static Stream<Arguments> should_return_correct_score_when_playerA_scores() {
                return Stream.of(
                        Arguments.of(loveAll(), fifteenLove()),
                        Arguments.of(fifteenLove(), thirtyLove()),
                        Arguments.of(thirtyForty(), deuce()),
                        Arguments.of(deuce(), advantagePlayerA()),
                        Arguments.of(advantagePlayerB(), deuce()),
                        Arguments.of(advantagePlayerA(), gameForty()),
                        Arguments.of(fortyThirty(), gameThirty())
                );
            }

            @ParameterizedTest
            @MethodSource
            void should_return_correct_score_when_playerA_scores(GameScore before, GameScore expectedAfter) {
                assertThat(before.scorePointForPlayerA()).isEqualTo(expectedAfter);
            }
        }

        @Nested
        class ScoreForPlayerB {
            private static Stream<Arguments> should_return_correct_score_when_playerB_scores() {
                return Stream.of(
                        Arguments.of(loveAll(), loveFifteen()),
                        Arguments.of(loveFifteen(), loveThirty()),
                        Arguments.of(loveThirty(), loveForty()),
                        Arguments.of(loveForty(), gameToLovePlayerB()),
                        Arguments.of(deuce(), advantagePlayerB()),
                        Arguments.of(advantagePlayerA(), deuce()),
                        Arguments.of(fortyThirty(), deuce()),
                        Arguments.of(advantagePlayerB(), fortyGame())
                );
            }

            @ParameterizedTest
            @MethodSource
            void should_return_correct_score_when_playerB_scores(GameScore score, GameScore expectedAfter) {
                assertThat(score.scorePointForPlayerB()).isEqualTo(expectedAfter);
            }
        }

        @Nested
        class TryToScoreWhenThereIsAlreadyAWinner {
            private static Stream<Arguments> should_throw_exception_if_trying_to_score_when_there_is_a_winner() {
                return Stream.of(
                        Arguments.of(gameForty()),
                        Arguments.of(fortyGame())
                );
            }

            @ParameterizedTest
            @MethodSource
            void should_throw_exception_if_trying_to_score_when_there_is_a_winner(GameScore aGameAlreadyWon) {
                // WHEN score for player A
                assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerA)
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("cannot score when there is a winner");

                // WHEN score for player B
                assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerB)
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("cannot score when there is a winner");
            }
        }
    }

    @Nested
    class Formatting {
        @Test
        void format_correctly_when_playerB_won() {
            assertThat(fortyGame().format()).isEqualTo("Player B wins the game");
        }
    }
}
