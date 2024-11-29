package org.example.core;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.With;

import static org.example.core.PlayerScore.*;

@EqualsAndHashCode
@ToString
@AllArgsConstructor
@With
public final class Score {
    private final int playerAScore;
    private final int playerBScore;

    public Score scorePointForPlayerA() {
        validateNoWinner();
        return this.withPlayerAScore(playerAScore + 1);
    }

    public Score scorePointForPlayerB() {
        validateNoWinner();
        return this.withPlayerBScore(playerBScore + 1);
    }

    public boolean playerAWon() {
        return playerWon(playerAScore, playerBScore);
    }

    public boolean playerBWon() {
        return playerWon(playerBScore, playerAScore);
    }

    private boolean playerWon(int score, int scoreOther) {
        return scored4PointsOrMore(score) && leadsBy2PointsOrMore(score, scoreOther);
    }

    public boolean isDeuce() {
        return bothPlayersScored3PointsOrMore() && bothScoresAreEqual();
    }

    public boolean isAdvantagePlayerA() {
        return hasAdvantage(playerAScore, playerBScore);
    }

    private boolean hasAdvantage(int score, int scoreOther) {
        if (!scored4PointsOrMore(score)) return false;
        return leadsByOnePoint(score, scoreOther);
    }

    public boolean isAdvantagePlayerB() {
        return hasAdvantage(playerBScore, playerAScore);
    }

    boolean isScoreEquivalentTo(Score other) {
        return this.isDeuce() && other.isDeuce()
               || this.isAdvantagePlayerA() && other.isAdvantagePlayerA()
               || this.isAdvantagePlayerB() && other.isAdvantagePlayerB()
               || this.playerAWon() && other.playerAWon()
               || this.playerBWon() && other.playerBWon()
               || this.scoreDetailsEqualTo(other);
    }

    private void validateNoWinner() {
        if (playerAWon() || playerBWon()) {
            throw new IllegalStateException("Cannot score when there is a winner");
        }
    }

    private boolean scored4PointsOrMore(int score) {
        return score >= 4;
    }

    private boolean leadsBy2PointsOrMore(int score, int scoreOther) {
        return score >= scoreOther + 2;
    }

    private boolean bothScoresAreEqual() {
        return playerAScore == playerBScore;
    }

    private boolean bothPlayersScored3PointsOrMore() {
        return playerBScore >= 3 && playerAScore >= 3;
    }

    private boolean leadsByOnePoint(int score, int scoreOther) {
        return score == scoreOther + 1;
    }

    private boolean scoreDetailsEqualTo(Score other) {
        return playerAScore == other.playerAScore && playerBScore == other.playerBScore;
    }

    public PlayerScore getPlayerAScore() {
        return getPlayerScore(playerAScore, playerBScore);
    }

    public PlayerScore getPlayerBScore() {
        return getPlayerScore(playerBScore, playerAScore);
    }

    private PlayerScore getPlayerScore(int score, int scoreOther) {
        if (playerWon(score, scoreOther)) {
            return GAME;
        } else if (bothPlayersScored3PointsOrMore() && leadsBy2PointsOrMore(scoreOther, score)) {
            return FORTY;
        } else if (hasAdvantage(score, scoreOther)) {
            return ADVANTAGE;
        } else if (hasAdvantage(scoreOther, score)) {
            return FORTY;
        } else if (isDeuce()) {
            return FORTY;
        } else if (score == 0) {
            return LOVE;
        } else if (score == 1) {
            return FIFTEEN;
        } else if (score == 2) {
            return THIRTY;
        } else if (score == 3) {
            return FORTY;
        } else {
            throw new IllegalArgumentException("should never happen");
        }
    }
}
