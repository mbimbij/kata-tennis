package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;
import lombok.With;

@EqualsAndHashCode(callSuper = false)
@ToString
@With
@Getter
public class IntegerBasedGameScore extends GameScore {
    private final int playerAScore;
    private final int playerBScore;

    public IntegerBasedGameScore(int playerAScore, int playerBScore) {
        this.playerAScore = playerAScore;
        this.playerBScore = playerBScore;
    }

    @Override
    public GameScore scorePointForPlayerA() {
        validateNoWinner();
        return this.withPlayerAScore(playerAScore + 1);
    }

    @Override
    public GameScore scorePointForPlayerB() {
        validateNoWinner();
        return this.withPlayerBScore(playerBScore + 1);
    }

    private void validateNoWinner() {
        if (playerAWon() || playerBWon()) {
            throw new IllegalStateException("Cannot score when there is a winner");
        }
    }

    @Override
    protected boolean playerAWon() {
        return playerAScore >= 4 && playerAScore >= playerBScore + 2;
    }

    @Override
    protected boolean playerBWon() {
        return playerBScore >= 4 && playerBScore >= playerAScore + 2;
    }

    @Override
    protected boolean isDeuce() {
        return playerAScore == playerBScore && playerAScore >= 3;
    }

    @Override
    protected boolean isAdvantagePlayerA() {
        return playerAScore >= 4 && playerBScore == playerAScore - 1;
    }

    @Override
    protected boolean isAdvantagePlayerB() {
        return playerBScore >= 4 && playerAScore == playerBScore - 1;
    }

    @Override
    protected boolean scoreDetailsEqual(GameScore o) {
        IntegerBasedGameScore that = ((IntegerBasedGameScore) o);
        return playerAScore == that.playerAScore && playerBScore == that.playerBScore;
    }
}
