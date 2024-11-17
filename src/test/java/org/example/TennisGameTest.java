package org.example;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.PlayerScore.*;

class TennisGameTest {

    @Test
    void should_score_deuce_when_30_40_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.a30_40Game();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(TestFixtures.deuce());
    }

    @Test
    void should_score_deuce_when_40_30_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.a40_30Game();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(TestFixtures.deuce());
    }

    @Test
    void should_score_advantage_to_playerA_when_deuce_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.a40_40Game();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(TestFixtures.advantagePlayerA());
    }

    @Test
    void should_score_advantage_to_playerB_when_deuce_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.a40_40Game();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(TestFixtures.advantagePlayerB());
    }

    @Test
    void should_score_deuce_when_advantage_to_playerA_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.advantagePlayerAGame();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(TestFixtures.deuce());
    }

    @Test
    void should_score_deuce_when_advantage_to_playerB_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.advantagePlayerBGame();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(TestFixtures.deuce());
    }

    @Test
    void playerA_should_win_when_advantage_to_playerA_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.advantagePlayerAGame();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(new GameScore(WIN, FORTY));
    }

    @Test
    void playerA_should_win_when_40_30_and_playerA_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.a40_30Game();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerA();

        // THEN
        assertThat(gameScore).isEqualTo(new GameScore(WIN, THIRTY));
    }

    @Test
    void playerB_should_win_when_30_40_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.a30_40Game();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(new GameScore(THIRTY, WIN));
    }

    @Test
    void playerB_should_win_when_advantage_to_playerB_and_playerB_scores() {
        // GIVEN
        TennisGame tennisGame = TestFixtures.advantagePlayerBGame();

        // WHEN
        GameScore gameScore = tennisGame.scorePointForPlayerB();

        // THEN
        assertThat(gameScore).isEqualTo(new GameScore(FORTY, WIN));
    }

    @Test
    void should_throw_exception_if_trying_to_score_when_there_is_a_winner() {
        // GIVEN
        TennisGame aGameAlreadyWon = aGameWonByPlayerA();

        // WHEN
        assertThatThrownBy(aGameAlreadyWon::scorePointForPlayerA)
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("cannot score when there is a winner");
    }

    private static TennisGame aGameWonByPlayerA() {
        return new TennisGame(WIN, FORTY);
    }
}
