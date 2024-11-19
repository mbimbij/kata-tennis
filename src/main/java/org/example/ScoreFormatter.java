package org.example;

public class ScoreFormatter {
    protected String getDefaultFormattedScore(Score gameScore) {
        return "Player A : %s / Player B : %s".formatted(this.getFormattedValue(gameScore.playerAScore()),
                this.getFormattedValue(gameScore.playerBScore()));
    }

    private String getFormattedValue(int score) {
        return switch (score) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default ->
                    throw new IllegalArgumentException("Invalid score: %d. Should be a 'deuce', an 'advantage' or a 'win'."
                            .formatted(score));
        };
    }

    public String format(Score gameScore) {
        if (gameScore.playerAWon()) {
            return "Player A wins the game";
        }
        if (gameScore.playerBWon()) {
            return "Player B wins the game";
        }
        if (gameScore.isDeuce()) {
            return "Deuce";
        }
        if (gameScore.isAdvantagePlayerA()) {
            return "Advantage Player A";
        }
        if (gameScore.isAdvantagePlayerB()) {
            return "Advantage Player B";
        }
        return getDefaultFormattedScore(gameScore);
    }
}