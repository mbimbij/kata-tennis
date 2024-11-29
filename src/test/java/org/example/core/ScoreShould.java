package org.example.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.core.PlayerScore.*;
import static org.example.core.PlayerScore.THIRTY;

class ScoreShould {
    private static ScoreFactory factory = ScoreFactory.getInstance();

    @ParameterizedTest
    @MethodSource
    void update_score_when_playerA_scores(Score score, Score expectedAfter) {
        boolean scoresEqual = score.scorePointForPlayerA().isScoreEquivalentTo(expectedAfter);
        assertThat(scoresEqual).isTrue();
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

    @ParameterizedTest
    @MethodSource
    void test_score_equivalence_appropriately(Score score, Score otherScore) {
        assertThat(score.isScoreEquivalentTo(otherScore)).isTrue();
    }

    @ParameterizedTest
    @MethodSource
    void return_player_score_as_enum(Score score, PlayerScore expectedPlayerAScore, PlayerScore expectedPlayerBScore) {
        assertThat(score.getPlayerAScore()).as("bad player A score").isEqualTo(expectedPlayerAScore);
        assertThat(score.getPlayerBScore()).as("bad player B score").isEqualTo(expectedPlayerBScore);
    }

    private static Stream<Arguments> update_score_when_playerA_scores() {
        return Stream.of(
                Arguments.of(factory.loveAll(), factory.fifteenLove()),
                Arguments.of(factory.fifteenLove(), factory.thirtyLove()),
                Arguments.of(factory.thirtyLove(), factory.fortyLove()),
                Arguments.of(factory.thirtyForty(), factory.deuce()),
                Arguments.of(factory.deuce(), factory.advantagePlayerA()),
                Arguments.of(factory.advantagePlayerB(), factory.deuce()),
                Arguments.of(factory.advantagePlayerA(), factory.gameFortyPlayerA()),
                Arguments.of(factory.fortyThirty(), factory.gameThirtyPlayerA())
        );
    }

    private static Stream<Arguments> update_score_when_playerB_scores() {
        return Stream.of(
                Arguments.of(factory.loveAll(), factory.loveFifteen()),
                Arguments.of(factory.loveFifteen(), factory.loveThirty()),
                Arguments.of(factory.loveThirty(), factory.loveForty()),
                Arguments.of(factory.loveForty(), factory.gameToLovePlayerB()),
                Arguments.of(factory.deuce(), factory.advantagePlayerB()),
                Arguments.of(factory.advantagePlayerA(), factory.deuce()),
                Arguments.of(factory.fortyThirty(), factory.deuce()),
                Arguments.of(factory.advantagePlayerB(), factory.gameFortyPlayerB())
        );
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

    private static Stream<Arguments> return_player_score_as_enum() {
        Arguments[] arguments = {
                Arguments.of(new Score(0, 0), LOVE, LOVE),
                Arguments.of(new Score(1, 0), FIFTEEN, LOVE),
                Arguments.of(new Score(2, 0), THIRTY, LOVE),
                Arguments.of(new Score(3, 0), FORTY, LOVE),
                Arguments.of(new Score(4, 0), GAME, LOVE),

                Arguments.of(new Score(0, 1), LOVE, FIFTEEN),
                Arguments.of(new Score(0, 2), LOVE, THIRTY),
                Arguments.of(new Score(0, 3), LOVE, FORTY),
                Arguments.of(new Score(0, 4), LOVE, GAME),

                Arguments.of(new Score(1, 1), FIFTEEN, FIFTEEN),
                Arguments.of(new Score(3, 2), FORTY, THIRTY),

                Arguments.of(new Score(3, 3), FORTY, FORTY),
                Arguments.of(new Score(4, 4), FORTY, FORTY),
                Arguments.of(new Score(5, 5), FORTY, FORTY),

                Arguments.of(new Score(4, 3), ADVANTAGE, FORTY),
                Arguments.of(new Score(5, 4), ADVANTAGE, FORTY),
                Arguments.of(new Score(6, 5), ADVANTAGE, FORTY),

                Arguments.of(new Score(3, 4), FORTY, ADVANTAGE),
                Arguments.of(new Score(4, 5), FORTY, ADVANTAGE),
                Arguments.of(new Score(5, 6), FORTY, ADVANTAGE),

                Arguments.of(new Score(5, 3), GAME, FORTY),
                Arguments.of(new Score(6, 4), GAME, FORTY),
                Arguments.of(new Score(7, 5), GAME, FORTY),
        };
        return Stream.of(arguments);
    }
}
