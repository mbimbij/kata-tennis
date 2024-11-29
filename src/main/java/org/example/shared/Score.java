package org.example.shared;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.With;
import lombok.experimental.FieldDefaults;

import java.util.Objects;

import static org.example.shared.PlayerScore.*;

@AllArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@With
public final class Score {
    int playerAScore;
    int playerBScore;

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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (Score) obj;
        return this.playerAScore == that.playerAScore &&
               this.playerBScore == that.playerBScore;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerAScore, playerBScore);
    }

    @Override
    public String toString() {
        return "Score[" +
               "playerAScore=" + playerAScore + ", " +
               "playerBScore=" + playerBScore + ']';
    }

}
