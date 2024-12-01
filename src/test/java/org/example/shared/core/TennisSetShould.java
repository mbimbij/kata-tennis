package org.example.shared.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TennisSetShould {

    private final static GameScoreFactory factory = GameScoreFactory.getInstance();

    @ParameterizedTest
    @MethodSource
    void define_when_set_is_ended_appropriately(SetScore setScore,
                                                GameScore gameScore,
                                                boolean expectedIsOver,
                                                Player expectedWinner) {
        TennisSet tennisSet = new TennisSet(setScore, gameScore);
        assertThat(tennisSet.isOver()).isEqualTo(expectedIsOver);
        assertThat(tennisSet.getWinner()).isEqualTo(expectedWinner);
    }

    @ParameterizedTest
    @MethodSource
    void score_point_for_player(SetScore setScoreInit,
                                GameScore gameScoreInit,
                                Player playerToScore,
                                SetScore expectedSetScore,
                                GameScore expectedGameScore,
                                boolean expectedIsSetOver,
                                Player expectedWinner) {
        // GIVEN
        TennisSet tennisSet = new TennisSet(setScoreInit, gameScoreInit);

        // WHEN
        switch (playerToScore) {
            case A -> tennisSet.scorePointForPlayerA();
            case B -> tennisSet.scorePointForPlayerB();
        }

        // THEN
        assertThat(tennisSet.getSetScore()).isEqualTo(expectedSetScore);
        assertThat(tennisSet.getCurrentGameScore()).isEqualTo(expectedGameScore);
        assertThat(tennisSet.isOver()).isEqualTo(expectedIsSetOver);
        assertThat(tennisSet.getWinner()).isEqualTo(expectedWinner);
    }

    @Test
    void cannot_score_point_if_set_is_over() {
        // GIVEN
        TennisSet tennisSet = new TennisSet(6, 2);

        // WHEN
        assertThatThrownBy(tennisSet::scorePointForPlayerA)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage(TennisSet.CANNOT_SCORE_ON_OVER_SET_ERROR_MESSAGE);
    }

    private static Stream<Arguments> define_when_set_is_ended_appropriately() {
        Arguments[] arguments = new Arguments[]{
                Arguments.of(new SetScore(0, 0), factory.loveAll(), false, null),
                Arguments.of(new SetScore(2, 4), factory.advantagePlayerA(), false, null),
                Arguments.of(new SetScore(6, 5), factory.loveForty(), false, null),
                Arguments.of(new SetScore(6, 6), factory.fortyForty(), false, null),
                Arguments.of(new SetScore(6, 2), null, true, Player.A),
                Arguments.of(new SetScore(7, 5), null, true, Player.A),
                Arguments.of(new SetScore(7, 6), null, true, Player.A),
                Arguments.of(new SetScore(2, 6), null, true, Player.B),
                Arguments.of(new SetScore(5, 7), null, true, Player.B),
                Arguments.of(new SetScore(6, 7), null, true, Player.B),
        };
        return Stream.of(arguments);
    }

    private static Stream<Arguments> score_point_for_player() {
        Arguments[] arguments = new Arguments[]{
                Arguments.of(new SetScore(0, 0), factory.loveAll(), Player.A, new SetScore(0, 0), factory.fifteenLove(), false, null),
                Arguments.of(new SetScore(0, 0), factory.fortyLove(), Player.A, new SetScore(1, 0), factory.loveAll(), false, null),
                Arguments.of(new SetScore(5, 5), factory.fortyLove(), Player.A, new SetScore(6, 5), factory.loveAll(), false, null),
                Arguments.of(new SetScore(5, 6), factory.fortyLove(), Player.A, new SetScore(6, 6), factory.loveAll(), false, null),
                Arguments.of(new SetScore(5, 2), factory.fortyLove(), Player.A, new SetScore(6, 2), factory.loveAll(), true, Player.A),

                Arguments.of(new SetScore(0, 0), factory.loveAll(), Player.B, new SetScore(0, 0), factory.loveFifteen(), false, null),
                Arguments.of(new SetScore(0, 0), factory.loveForty(), Player.B, new SetScore(0, 1), factory.loveAll(), false, null),
                Arguments.of(new SetScore(5, 5), factory.loveForty(), Player.B, new SetScore(5, 6), factory.loveAll(), false, null),
                Arguments.of(new SetScore(6, 5), factory.loveForty(), Player.B, new SetScore(6, 6), factory.loveAll(), false, null),
                Arguments.of(new SetScore(2, 5), factory.loveForty(), Player.B, new SetScore(2, 6), factory.loveAll(), true, Player.B),
        };
        return Stream.of(arguments);
    }
}
