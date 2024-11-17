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
        return false;
    }

    @Override
    protected boolean playerBWon() {
        return playerBScore == 4;
    }

    @Override
    protected boolean isDeuce() {
        return false;
    }

    @Override
    protected boolean isAdvantagePlayerA() {
        return false;
    }

    @Override
    protected boolean isAdvantagePlayerB() {
        return false;
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
