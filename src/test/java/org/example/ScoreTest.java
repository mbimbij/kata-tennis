package org.example;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreTest {
    private static final ScoreFactory factory = new ScoreFactory();

    @Nested
    class Computing {

        @Nested
        class ScorePoint {

            private static Stream<Arguments> should_update_score_when_playerA_scores() {
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
            void should_update_score_when_playerA_scores(Score score, Score expectedAfter) {
                boolean scoresEqual = score.scorePointForPlayerA().isScoreEquivalentTo(expectedAfter);
                assertThat(scoresEqual).isTrue();
            }

            private static Stream<Arguments> should_update_score_when_playerB_scores() {
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
            void should_update_score_when_playerB_scores(Score score, Score expectedAfter) {
                boolean scoresEqual = score.scorePointForPlayerB().isScoreEquivalentTo(expectedAfter);
                assertThat(scoresEqual).isTrue();
            }

            @ParameterizedTest
            @CsvSource({
                    "4, 0",
                    "0,4"
            })
            void should_throw_exception_if_trying_to_score_for_playerA_when_there_is_a_winner(int playerAScore, int playerBScore) {
                Score aGameAlreadyWon = new Score(playerAScore, playerBScore);
                assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerA)
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("Cannot score when there is a winner");
            }

            @ParameterizedTest
            @CsvSource({
                    "4, 0",
                    "0,4"
            })
            void should_throw_exception_if_trying_to_score_for_playerB_when_there_is_a_winner(int playerAScore, int playerBScore) {
                Score aGameAlreadyWon = new Score(playerAScore, playerBScore);
                assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerB)
                        .isInstanceOf(IllegalStateException.class)
                        .hasMessage("Cannot score when there is a winner");
            }
        }
    }

    @Nested
    class Formatting {
        @ParameterizedTest
        @CsvFileSource(files = "src/test/resources/integer_based_scoring_format_test_cases.csv", useHeadersInDisplayName = true)
        void integer_based_specific_tests(int playerAScore, int playerBScore, String expectedFormattedScore) {
            // GIVEN
            Score gameScore = new Score(playerAScore, playerBScore);
            ScoreFormatter scoreFormatter = new ScoreFormatter();

            // WHEN
            String formattedScore = scoreFormatter.format(gameScore);

            // THEN
            assertThat(formattedScore).isEqualTo(expectedFormattedScore);
        }
    }
}
