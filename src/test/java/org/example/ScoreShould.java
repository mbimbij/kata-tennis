package org.example;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ScoreShould {

    private static Stream<Arguments> update_score_when_playerA_scores() {
        return Stream.of(
                Arguments.of(Scores.loveAll(), Scores.fifteenLove()),
                Arguments.of(Scores.fifteenLove(), Scores.thirtyLove()),
                Arguments.of(Scores.thirtyLove(), Scores.fortyLove()),
                Arguments.of(Scores.thirtyForty(), Scores.deuce()),
                Arguments.of(Scores.deuce(), Scores.advantagePlayerA()),
                Arguments.of(Scores.advantagePlayerB(), Scores.deuce()),
                Arguments.of(Scores.advantagePlayerA(), Scores.gameFortyPlayerA()),
                Arguments.of(Scores.fortyThirty(), Scores.gameThirtyPlayerA())
        );
    }

    @ParameterizedTest
    @MethodSource
    void update_score_when_playerA_scores(Score score, Score expectedAfter) {
        boolean scoresEqual = score.scorePointForPlayerA().isScoreEquivalentTo(expectedAfter);
        assertThat(scoresEqual).isTrue();
    }

    private static Stream<Arguments> update_score_when_playerB_scores() {
        return Stream.of(
                Arguments.of(Scores.loveAll(), Scores.loveFifteen()),
                Arguments.of(Scores.loveFifteen(), Scores.loveThirty()),
                Arguments.of(Scores.loveThirty(), Scores.loveForty()),
                Arguments.of(Scores.loveForty(), Scores.gameToLovePlayerB()),
                Arguments.of(Scores.deuce(), Scores.advantagePlayerB()),
                Arguments.of(Scores.advantagePlayerA(), Scores.deuce()),
                Arguments.of(Scores.fortyThirty(), Scores.deuce()),
                Arguments.of(Scores.advantagePlayerB(), Scores.gameFortyPlayerB())
        );
    }

    @ParameterizedTest
    @MethodSource
    void update_score_when_playerB_scores(Score score, Score expectedAfter) {
        boolean scoresEqual = score.scorePointForPlayerB().isScoreEquivalentTo(expectedAfter);
        assertThat(scoresEqual).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "4, 0",
            "0,4"
    })
    void throw_exception_if_trying_to_score_for_playerA_when_there_is_a_winner(int playerAScore, int playerBScore) {
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
    void throw_exception_if_trying_to_score_for_playerB_when_there_is_a_winner(int playerAScore, int playerBScore) {
        Score aGameAlreadyWon = new Score(playerAScore, playerBScore);
        assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerB)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot score when there is a winner");
    }

    private static Stream<Arguments> test_score_equivalence_appropriately() {
        return Stream.of(
                Arguments.of(new Score(3, 3), new Score(4, 4)),
                Arguments.of(new Score(4, 3), new Score(5, 4)),
                Arguments.of(new Score(4, 3), new Score(6, 5)),
                Arguments.of(new Score(3, 4), new Score(4, 5)),
                Arguments.of(new Score(3, 4), new Score(5, 6)),
                Arguments.of(new Score(5, 3), new Score(6, 4)),
                Arguments.of(new Score(5, 3), new Score(7, 5)),
                Arguments.of(new Score(5, 3), new Score(4, 0)),
                Arguments.of(new Score(5, 3), new Score(4, 1)),
                Arguments.of(new Score(3, 5), new Score(4, 6)),
                Arguments.of(new Score(3, 5), new Score(5, 7)),
                Arguments.of(new Score(3, 5), new Score(0, 4)),
                Arguments.of(new Score(3, 5), new Score(1, 4))
                );
    }

    @ParameterizedTest
    @MethodSource
    void test_score_equivalence_appropriately(Score score, Score otherScore) {
        assertThat(score.isScoreEquivalentTo(otherScore)).isTrue();
    }
}
