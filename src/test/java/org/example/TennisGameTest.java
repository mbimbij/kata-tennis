package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.PlayerScore.*;

class TennisGameTest {

//    @Test
//    void should_score_deuce_when_40_30_and_playerB_scores() {
//        // GIVEN
//        GameScore score = TestFixtures.a40_30Score();
//
//        // WHEN
//        GameScore newScore = score.scorePointForPlayerB();
//
//        // THEN
//        assertThat(newScore).isEqualTo(TestFixtures.deuce());
//    }

    @Test
    void should_score_advantage_to_playerB_when_deuce_and_playerB_scores() {
        // GIVEN
        TennisGame score = TestFixtures.a40Vs40Game();

        // WHEN
        GameScore newScore = score.scorePointForPlayerB();

        // THEN
        assertThat(newScore).isEqualTo(TestFixtures.advantagePlayerBScore());
    }

    @Test
    void should_score_deuce_when_advantage_to_playerA_and_playerB_scores() {
        // GIVEN
        TennisGame score = TestFixtures.advantagePlayerAGame();

        // WHEN
        GameScore newScore = score.scorePointForPlayerB();

        // THEN
        assertThat(newScore).isEqualTo(TestFixtures.deuce());
    }

    @Test
    void playerB_should_win_when_30_40_and_playerB_scores() {
        // GIVEN
        TennisGame score = TestFixtures.a30Vs40Game();

        // WHEN
        GameScore newScore = score.scorePointForPlayerB();

        // THEN
        assertThat(newScore).isEqualTo(new GameScore(THIRTY, WIN));
    }

    @Test
    void playerB_should_win_when_advantage_to_playerB_and_playerB_scores() {
        // GIVEN
        TennisGame score = TestFixtures.advantagePlayerBGame();

        // WHEN
        GameScore newScore = score.scorePointForPlayerB();

        // THEN
        assertThat(newScore).isEqualTo(new GameScore(FORTY, WIN));
    }

}
