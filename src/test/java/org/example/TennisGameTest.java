package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.SoftAssertions.assertSoftly;
import static org.example.PlayerScore.*;

class TennisGameTest {
    @Test
    void score_for_both_players_should_be_deuce_when_expected() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(THIRTY, FORTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertSoftly(sa -> {
            sa.assertThat(gameScore.playerAScore()).isEqualTo(DEUCE);
            sa.assertThat(gameScore.playerBScore()).isEqualTo(DEUCE);
        });
    }
}
