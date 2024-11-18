package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class GameScoreTest {
    private static final GameScoreFactory factory = new IntegerBasedGameScoreFactory();

    @Nested
    class Computing {
        @Nested
        class ScoreForPlayerA {

            private static Stream<Arguments> should_return_correct_score_when_playerA_scores() {
                return Stream.of(
                        Arguments.of(factory.loveAll(), factory.fifteenLove()),
                        Arguments.of(factory.fifteenLove(), factory.thirtyLove()),
                        Arguments.of(factory.thirtyLove(), factory.fortyLove()),
                        Arguments.of(factory.thirtyForty(), factory.deuce()),
                        Arguments.of(factory.deuce(), factory.advantagePlayerA()),
                        Arguments.of(factory.advantagePlayerB(), factory.deuce()),
                        Arguments.of(factory.advantagePlayerA(), factory.gameForty()),
                        Arguments.of(factory.fortyThirty(), factory.gameThirty())
                );
            }

            @ParameterizedTest
            @MethodSource
            void should_return_correct_score_when_playerA_scores(GameScore score, GameScore expectedAfter) {
                boolean scoresEqual = score.scorePointForPlayerA().scoreEquals(expectedAfter);
                assertThat(scoresEqual).isTrue();
            }
        }

        @Nested
        class ScoreForPlayerB {
            private static Stream<Arguments> should_return_correct_score_when_playerB_scores() {
                return Stream.of(
                        Arguments.of(factory.loveAll(), factory.loveFifteen()),
                        Arguments.of(factory.loveFifteen(), factory.loveThirty()),
                        Arguments.of(factory.loveThirty(), factory.loveForty()),
                        Arguments.of(factory.loveForty(), factory.gameToLovePlayerB()),
                        Arguments.of(factory.deuce(), factory.advantagePlayerB()),
                        Arguments.of(factory.advantagePlayerA(), factory.deuce()),
                        Arguments.of(factory.fortyThirty(), factory.deuce()),
                        Arguments.of(factory.advantagePlayerB(), factory.fortyGame())
                );
            }

            @ParameterizedTest
            @MethodSource
            void should_return_correct_score_when_playerB_scores(GameScore score, GameScore expectedAfter) {
                boolean scoresEqual = score.scorePointForPlayerB().scoreEquals(expectedAfter);
                assertThat(scoresEqual).isTrue();
            }
        }

        @Nested
        class TryToScoreWhenThereIsAlreadyAWinner {
            private static Stream<Arguments> should_throw_exception_if_trying_to_score_when_there_is_a_winner() {
                return Stream.of(
                        Arguments.of(factory.gameForty()),
                        Arguments.of(factory.fortyGame())
                );
            }

            @ParameterizedTest
            @MethodSource
            void should_throw_exception_if_trying_to_score_when_there_is_a_winner(GameScore aGameAlreadyWon) {
                // WHEN score for player A
                assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerA)
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("Cannot score when there is a winner");

                // WHEN score for player B
                assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerB)
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("Cannot score when there is a winner");
            }
        }
    }

    @Nested
    class Formatting {

        private ScoreFormatter scoreFormatter = new ScoreFormatter();
        ;

        private static Stream<Arguments> should_format_correctly() {
            return Stream.of(
                    Arguments.of(factory.loveAll(), "Player A : 0 / Player B : 0"),
                    Arguments.of(factory.fifteenLove(), "Player A : 15 / Player B : 0"),
                    Arguments.of(factory.thirtyLove(), "Player A : 30 / Player B : 0"),
                    Arguments.of(factory.fortyLove(), "Player A : 40 / Player B : 0"),
                    Arguments.of(factory.loveFifteen(), "Player A : 0 / Player B : 15"),
                    Arguments.of(factory.loveThirty(), "Player A : 0 / Player B : 30"),
                    Arguments.of(factory.loveForty(), "Player A : 0 / Player B : 40"),
                    Arguments.of(factory.thirtyForty(), "Player A : 30 / Player B : 40"),
                    Arguments.of(factory.fortyThirty(), "Player A : 40 / Player B : 30"),
                    Arguments.of(factory.deuce(), "Deuce"),
                    Arguments.of(factory.advantagePlayerA(), "Advantage Player A"),
                    Arguments.of(factory.advantagePlayerB(), "Advantage Player B"),
                    Arguments.of(factory.gameForty(), "Player A wins the game"),
                    Arguments.of(factory.gameThirty(), "Player A wins the game"),
                    Arguments.of(factory.gameToLovePlayerB(), "Player B wins the game"),
                    Arguments.of(factory.fortyGame(), "Player B wins the game")
            );
        }

        @ParameterizedTest
        @MethodSource
        void should_format_correctly(GameScore gameScore, String expected) {
            assertThat(scoreFormatter.format(gameScore)).isEqualTo(expected);
        }

        @ParameterizedTest
        @CsvFileSource(files = "src/test/resources/integer_based_scoring_format_test_cases.csv", useHeadersInDisplayName = true)
        void integer_based_specific_tests(int playerAScore, int playerBScore, String expectedFormattedScore) {
            GameScore gameScore = new IntegerBasedGameScore(playerAScore, playerBScore);
            assertThat(scoreFormatter.format(gameScore)).isEqualTo(expectedFormattedScore);
        }
    }
}
