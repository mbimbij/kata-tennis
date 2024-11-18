package org.example;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.With;

@EqualsAndHashCode(callSuper = false)
@ToString
@With
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
    protected String getDefaultFormattedScore(GameScore gameScore) {
        IntegerBasedGameScore integerBasedGameScore = (IntegerBasedGameScore) gameScore;
        return "Player A : %s / Player B : %s".formatted(this.getFormattedValue(integerBasedGameScore.playerAScore),
                this.getFormattedValue(integerBasedGameScore.playerBScore));
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

    private String getFormattedValue(int score) {
        return switch (score) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default ->
                    throw new IllegalArgumentException("Invalid score: " + score + ". Should be a 'deuce', an 'advantage' or a 'win'.");
        };
    }

    @Override
    protected boolean scoreDetailsEqual(GameScore o) {
        IntegerBasedGameScore that = ((IntegerBasedGameScore) o);
        return playerAScore == that.playerAScore && playerBScore == that.playerBScore;
    }
}
