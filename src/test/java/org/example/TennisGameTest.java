package org.example;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.PlayerScore.*;

class TennisGameTest {

    private final GameScore deuce = new GameScore(FORTY, FORTY);
    private final GameScore advantagePlayerA = new GameScore(ADVANTAGE, FORTY);
    private final GameScore advantagePlayerB = new GameScore(FORTY, ADVANTAGE);

    @Test
    void should_score_deuce_when_30_40_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(THIRTY, FORTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(deuce);
    }

    @Test

    void should_score_deuce_when_40_30_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(FORTY, THIRTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(deuce);
    }

    @Test
    void should_score_advantage_to_playerA_when_deuce_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(FORTY, FORTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(advantagePlayerA);
    }

    @Test
    void should_score_advantage_to_playerB_when_deuce_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(FORTY, FORTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(advantagePlayerB);
    }

    @Test
    void should_score_deuce_when_advantage_to_playerA_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(ADVANTAGE, FORTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(deuce);
    }

    @Test
    void should_score_deuce_when_advantage_to_playerB_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(FORTY, ADVANTAGE);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(deuce);
    }

    @Test
    void playerA_should_win_when_advantage_to_playerA_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(ADVANTAGE, FORTY);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(new GameScore(WIN, FORTY));
    }

    @Test
    void playerB_should_win_when_advantage_to_playerB_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(FORTY, ADVANTAGE);

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(new GameScore(FORTY, WIN));
    }

    @Test
    void should_throw_exception_if_trying_to_score_when_there_is_a_winner() {
        // GIVEN
        TennisGame tennisGame = new TennisGame(WIN, FORTY);

        // WHEN
        assertThatThrownBy(tennisGame::scorePointForPlayerA)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("cannot score when there is a winner");
    }
}
