package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class EnumBasedGameScoreTest {
    private static final GameScoreFactory gameScoreFactory = GameScoreFactory.getInstance();

    @Nested
    class Computing {
        @Nested
        class ScoreForPlayerA {
            private static Stream<Arguments> should_return_correct_score_when_playerA_scores() {
                return Stream.of(
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.loveAll(), EnumBasedGameScoreTest.gameScoreFactory.fifteenLove()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.fifteenLove(), EnumBasedGameScoreTest.gameScoreFactory.thirtyLove()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.thirtyForty(), EnumBasedGameScoreTest.gameScoreFactory.deuce()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.deuce(), EnumBasedGameScoreTest.gameScoreFactory.advantagePlayerA()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.advantagePlayerB(), EnumBasedGameScoreTest.gameScoreFactory.deuce()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.advantagePlayerA(), EnumBasedGameScoreTest.gameScoreFactory.gameForty()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.fortyThirty(), EnumBasedGameScoreTest.gameScoreFactory.gameThirty())
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
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.loveAll(), EnumBasedGameScoreTest.gameScoreFactory.loveFifteen()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.loveFifteen(), EnumBasedGameScoreTest.gameScoreFactory.loveThirty()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.loveThirty(), EnumBasedGameScoreTest.gameScoreFactory.loveForty()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.loveForty(), EnumBasedGameScoreTest.gameScoreFactory.gameToLovePlayerB()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.deuce(), EnumBasedGameScoreTest.gameScoreFactory.advantagePlayerB()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.advantagePlayerA(), EnumBasedGameScoreTest.gameScoreFactory.deuce()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.fortyThirty(), EnumBasedGameScoreTest.gameScoreFactory.deuce()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.advantagePlayerB(), EnumBasedGameScoreTest.gameScoreFactory.fortyGame())
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
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.gameForty()),
                        Arguments.of(EnumBasedGameScoreTest.gameScoreFactory.fortyGame())
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
