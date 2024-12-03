package org.example.scoring.core;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.scoring.core.PlayerGameScore.*;
import static org.example.scoring.core.PlayerGameScore.THIRTY;
import static org.example.scoring.core.GameScore.fromEnum;

class GameScoreShould {
    private static GameScoreFactory factory = GameScoreFactory.getInstance();

    @ParameterizedTest
    @MethodSource
    void update_score_when_playerA_scores(GameScore score, GameScore expectedAfter) {
        boolean scoresEqual = score.scorePointForPlayerA().isScoreEquivalentTo(expectedAfter);
        assertThat(scoresEqual).isTrue();
    }

    @ParameterizedTest
    @MethodSource
    void update_score_when_playerB_scores(GameScore score, GameScore expectedAfter) {
        boolean scoresEqual = score.scorePointForPlayerB().isScoreEquivalentTo(expectedAfter);
        assertThat(scoresEqual).isTrue();
    }

    @ParameterizedTest
    @CsvSource({
            "4, 0",
            "0,4"
    })
    void throw_exception_if_trying_to_score_for_playerA_when_there_is_a_winner(int playerAScore, int playerBScore) {
        GameScore aGameAlreadyWon = new GameScore(playerAScore, playerBScore);
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
        GameScore aGameAlreadyWon = new GameScore(playerAScore, playerBScore);
        assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerB)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Cannot score when there is a winner");
    }

    @ParameterizedTest
    @MethodSource
    void test_score_equivalence_appropriately(GameScore score, GameScore otherScore) {
        assertThat(score.isScoreEquivalentTo(otherScore)).isTrue();
    }

    @ParameterizedTest
    @MethodSource
    void return_player_score_as_enum(GameScore score, PlayerGameScore expectedPlayerAScore, PlayerGameScore expectedPlayerBScore) {
        assertThat(score.getPlayerAScore()).as("bad player A score").isEqualTo(expectedPlayerAScore);
        assertThat(score.getPlayerBScore()).as("bad player B score").isEqualTo(expectedPlayerBScore);
    }

    @ParameterizedTest
    @MethodSource
    void return_expected_score_from_enums(GameScore score, GameScore expected) {
        assertThat(score.isScoreEquivalentTo(expected)).isTrue();
    }

    private static Stream<Arguments> return_expected_score_from_enums() {
        Arguments[] arguments = new Arguments[]{
                Arguments.of(fromEnum(LOVE, LOVE), factory.loveAll()),
                Arguments.of(fromEnum(FIFTEEN, LOVE), factory.fifteenLove()),
                Arguments.of(fromEnum(THIRTY, LOVE), factory.thirtyLove()),
                Arguments.of(fromEnum(FORTY, LOVE), factory.fortyLove()),
                Arguments.of(fromEnum(GAME, LOVE), factory.gameToLovePlayerA()),

                Arguments.of(fromEnum(LOVE, FIFTEEN), factory.loveFifteen()),
                Arguments.of(fromEnum(LOVE, THIRTY), factory.loveThirty()),
                Arguments.of(fromEnum(LOVE, FORTY), factory.loveForty()),
                Arguments.of(fromEnum(LOVE, GAME), factory.gameToLovePlayerB()),

                Arguments.of(fromEnum(THIRTY, FORTY), factory.thirtyForty()),
                Arguments.of(fromEnum(FIFTEEN, THIRTY), factory.fifteenThirty()),

                Arguments.of(fromEnum(FORTY, FORTY), factory.deuce()),
                Arguments.of(fromEnum(ADVANTAGE, FORTY), factory.advantagePlayerA()),
                Arguments.of(fromEnum(FORTY, ADVANTAGE), factory.advantagePlayerB()),
        };
        return Stream.of(arguments);
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
                Arguments.of(new GameScore(3, 3), new GameScore(4, 4)),
                Arguments.of(new GameScore(4, 3), new GameScore(5, 4)),
                Arguments.of(new GameScore(4, 3), new GameScore(6, 5)),
                Arguments.of(new GameScore(3, 4), new GameScore(4, 5)),
                Arguments.of(new GameScore(3, 4), new GameScore(5, 6)),
                Arguments.of(new GameScore(5, 3), new GameScore(6, 4)),
                Arguments.of(new GameScore(5, 3), new GameScore(7, 5)),
                Arguments.of(new GameScore(5, 3), new GameScore(4, 0)),
                Arguments.of(new GameScore(5, 3), new GameScore(4, 1)),
                Arguments.of(new GameScore(3, 5), new GameScore(4, 6)),
                Arguments.of(new GameScore(3, 5), new GameScore(5, 7)),
                Arguments.of(new GameScore(3, 5), new GameScore(0, 4)),
                Arguments.of(new GameScore(3, 5), new GameScore(1, 4))
        );
    }

    private static Stream<Arguments> return_player_score_as_enum() {
        Arguments[] arguments = {
                Arguments.of(new GameScore(0, 0), LOVE, LOVE),
                Arguments.of(new GameScore(1, 0), FIFTEEN, LOVE),
                Arguments.of(new GameScore(2, 0), THIRTY, LOVE),
                Arguments.of(new GameScore(3, 0), FORTY, LOVE),
                Arguments.of(new GameScore(4, 0), GAME, LOVE),

                Arguments.of(new GameScore(0, 1), LOVE, FIFTEEN),
                Arguments.of(new GameScore(0, 2), LOVE, THIRTY),
                Arguments.of(new GameScore(0, 3), LOVE, FORTY),
                Arguments.of(new GameScore(0, 4), LOVE, GAME),

                Arguments.of(new GameScore(1, 1), FIFTEEN, FIFTEEN),
                Arguments.of(new GameScore(3, 2), FORTY, THIRTY),

                Arguments.of(new GameScore(3, 3), FORTY, FORTY),
                Arguments.of(new GameScore(4, 4), FORTY, FORTY),
                Arguments.of(new GameScore(5, 5), FORTY, FORTY),

                Arguments.of(new GameScore(4, 3), ADVANTAGE, FORTY),
                Arguments.of(new GameScore(5, 4), ADVANTAGE, FORTY),
                Arguments.of(new GameScore(6, 5), ADVANTAGE, FORTY),

                Arguments.of(new GameScore(3, 4), FORTY, ADVANTAGE),
                Arguments.of(new GameScore(4, 5), FORTY, ADVANTAGE),
                Arguments.of(new GameScore(5, 6), FORTY, ADVANTAGE),

                Arguments.of(new GameScore(5, 3), GAME, FORTY),
                Arguments.of(new GameScore(6, 4), GAME, FORTY),
                Arguments.of(new GameScore(7, 5), GAME, FORTY),
        };
        return Stream.of(arguments);
    }
}
