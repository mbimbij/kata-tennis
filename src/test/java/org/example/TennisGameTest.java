package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.example.GameScores.*;
import static org.example.PlayerScore.*;

class TennisGameTest {
    @Test
    void score_for_both_players_should_be_deuce_when_expected() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(THIRTY, FORTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(DEUCE);
    }

    @Test
    void should_score_advantage_to_playerA_when_deuce_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(FORTY, FORTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(ADVANTAGE_PLAYER_A);
    }

    @Test
    void should_score_advantage_to_playerB_when_deuce_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(FORTY, FORTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(ADVANTAGE_PLAYER_B);
    }
}
