package org.example;

import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.With;

@EqualsAndHashCode(callSuper = true)
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
        return this.withPlayerAScore(playerAScore + 1);
    }

    @Override
    public GameScore scorePointForPlayerB() {
        return this.withPlayerBScore(playerBScore + 1);
    }

    @Override
    protected String getDefaultFormattedScore() {
        return "Player A : %s / Player B : %s".formatted(getFormattedValue(playerAScore), getFormattedValue(playerBScore));
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

    public String getFormattedValue(int score) {
        return switch (score) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default ->
                    throw new IllegalArgumentException("Invalid score: " + score + ". Should be a 'deuce', an 'advantage' or a 'win'.");
        };
    }
}
