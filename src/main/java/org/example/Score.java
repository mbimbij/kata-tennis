package org.example;

import lombok.With;

@With
public record Score(int playerAScore, int playerBScore) {

    public Score scorePointForPlayerA() {
        validateNoWinner();
        return this.withPlayerAScore(playerAScore + 1);
    }

    public Score scorePointForPlayerB() {
        validateNoWinner();
        return this.withPlayerBScore(playerBScore + 1);
    }

    private void validateNoWinner() {
        if (playerAWon() || playerBWon()) {
            throw new IllegalStateException("Cannot score when there is a winner");
        }
    }

    boolean playerAWon() {
        return playerAScored4PointsOrMore() && playerALeadsBy2PointsOrMore();
    }

    boolean playerBWon() {
        return playerBScored4PointsOrMore() && playerBLeadsBy2Points();
    }

    boolean isDeuce() {
        return bothPlayersScored3PointsOrMore() && bothScoresEqual();
    }

    boolean isAdvantagePlayerA() {
        return playerAScored4PointsOrMore() && playerALeadsBy1Point();
    }

    boolean isAdvantagePlayerB() {
        return playerBScored4PointsOrMore() && playerBLeadsBy1Point();
    }

    private boolean playerAScored4PointsOrMore() {
        return playerAScore >= 4;
    }

    private boolean playerALeadsBy2PointsOrMore() {
        return playerAScore >= playerBScore + 2;
    }

    private boolean playerBScored4PointsOrMore() {
        return playerBScore >= 4;
    }

    private boolean playerBLeadsBy2Points() {
        return playerBScore >= playerAScore + 2;
    }

    private boolean bothScoresEqual() {
        return playerAScore == playerBScore;
    }

    private boolean bothPlayersScored3PointsOrMore() {
        return playerBScore >= 3 && playerAScore >= 3;
    }

    private boolean playerALeadsBy1Point() {
        return playerAScore == playerBScore + 1;
    }

    private boolean playerBLeadsBy1Point() {
        return playerBScore == playerAScore + 1;
    }

    private boolean scoreDetailsEqualTo(Score other) {
        return playerAScore == other.playerAScore && playerBScore == other.playerBScore;
    }

    public boolean isScoreEquivalentTo(Score other) {
        return this.isDeuce() && other.isDeuce()
               || this.isAdvantagePlayerA() && other.isAdvantagePlayerA()
               || this.isAdvantagePlayerB() && other.isAdvantagePlayerB()
               || this.playerAWon() && other.playerAWon()
               || this.playerBWon() && other.playerBWon()
               || this.scoreDetailsEqualTo(other);
    }
}
