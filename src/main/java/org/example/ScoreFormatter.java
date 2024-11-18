package org.example;

public class ScoreFormatter {
    protected String getDefaultFormattedScore(GameScore gameScore) {
        GameScore that = (GameScore) gameScore;
        return "Player A : %s / Player B : %s".formatted(this.getFormattedValue(that.getPlayerAScore()),
                this.getFormattedValue(that.getPlayerBScore()));
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

    public String format(GameScore gameScore) {
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
