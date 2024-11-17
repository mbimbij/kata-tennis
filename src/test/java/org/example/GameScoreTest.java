package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameScoreTest {
    private static GameScoreFactory gameScoreFactory = GameScoreFactory.getInstance();

    @Nested
    class Computing {
        @Nested
        class ScoreForPlayerA {
            private static Stream<Arguments> should_return_correct_score_when_playerA_scores() {
                return Stream.of(
                        Arguments.of(GameScoreTest.gameScoreFactory.loveAll(), GameScoreTest.gameScoreFactory.fifteenLove()),
                        Arguments.of(GameScoreTest.gameScoreFactory.fifteenLove(), GameScoreTest.gameScoreFactory.thirtyLove()),
                        Arguments.of(GameScoreTest.gameScoreFactory.thirtyForty(), GameScoreTest.gameScoreFactory.deuce()),
                        Arguments.of(GameScoreTest.gameScoreFactory.deuce(), GameScoreTest.gameScoreFactory.advantagePlayerA()),
                        Arguments.of(GameScoreTest.gameScoreFactory.advantagePlayerB(), GameScoreTest.gameScoreFactory.deuce()),
                        Arguments.of(GameScoreTest.gameScoreFactory.advantagePlayerA(), GameScoreTest.gameScoreFactory.gameForty()),
                        Arguments.of(GameScoreTest.gameScoreFactory.fortyThirty(), GameScoreTest.gameScoreFactory.gameThirty())
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
                        Arguments.of(GameScoreTest.gameScoreFactory.loveAll(), GameScoreTest.gameScoreFactory.loveFifteen()),
                        Arguments.of(GameScoreTest.gameScoreFactory.loveFifteen(), GameScoreTest.gameScoreFactory.loveThirty()),
                        Arguments.of(GameScoreTest.gameScoreFactory.loveThirty(), GameScoreTest.gameScoreFactory.loveForty()),
                        Arguments.of(GameScoreTest.gameScoreFactory.loveForty(), GameScoreTest.gameScoreFactory.gameToLovePlayerB()),
                        Arguments.of(GameScoreTest.gameScoreFactory.deuce(), GameScoreTest.gameScoreFactory.advantagePlayerB()),
                        Arguments.of(GameScoreTest.gameScoreFactory.advantagePlayerA(), GameScoreTest.gameScoreFactory.deuce()),
                        Arguments.of(GameScoreTest.gameScoreFactory.fortyThirty(), GameScoreTest.gameScoreFactory.deuce()),
                        Arguments.of(GameScoreTest.gameScoreFactory.advantagePlayerB(), GameScoreTest.gameScoreFactory.fortyGame())
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
                        Arguments.of(GameScoreTest.gameScoreFactory.gameForty()),
                        Arguments.of(GameScoreTest.gameScoreFactory.fortyGame())
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
            assertThat(gameScoreFactory.fortyGame().format()).isEqualTo("Player B wins the game");
        }
    }
}
