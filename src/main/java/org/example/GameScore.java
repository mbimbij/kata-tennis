package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

@EqualsAndHashCode(callSuper = false)
@ToString
@With
@Getter
public class GameScore {
    private final int playerAScore;
    private final int playerBScore;

    public GameScore(int playerAScore, int playerBScore) {
        this.playerAScore = playerAScore;
        this.playerBScore = playerBScore;
    }

    public GameScore scorePointForPlayerA() {
        validateNoWinner();
        return this.withPlayerAScore(playerAScore + 1);
    }

    public GameScore scorePointForPlayerB() {
        validateNoWinner();
        return this.withPlayerBScore(playerBScore + 1);
    }

    private void validateNoWinner() {
        if (playerAWon() || playerBWon()) {
            throw new IllegalStateException("Cannot score when there is a winner");
        }
    }

    protected boolean playerAWon() {
        return playerAScore >= 4 && playerAScore >= playerBScore + 2;
    }

    protected boolean playerBWon() {
        return playerBScore >= 4 && playerBScore >= playerAScore + 2;
    }

    protected boolean isDeuce() {
        return playerAScore == playerBScore && playerAScore >= 3;
    }

    protected boolean isAdvantagePlayerA() {
        return playerAScore >= 4 && playerBScore == playerAScore - 1;
    }

    protected boolean isAdvantagePlayerB() {
        return playerBScore >= 4 && playerAScore == playerBScore - 1;
    }

    protected boolean scoreDetailsEqual(GameScore o) {
        GameScore that = ((GameScore) o);
        return playerAScore == that.playerAScore && playerBScore == that.playerBScore;
    }

    public boolean scoreEquals(GameScore that) {
        return this.isDeuce() && that.isDeuce()
               || this.isAdvantagePlayerA() && that.isAdvantagePlayerA()
               || this.isAdvantagePlayerB() && that.isAdvantagePlayerB()
               || this.playerAWon() && that.playerAWon()
               || this.playerBWon() && that.playerBWon()
               || scoreDetailsEqual(that);
    }
}
