package org.example.domain;

public class ScoreFormatter {
    ScoreFormatter() {
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

    private String getDefaultFormattedScore(Score gameScore) {
        String formattedPlayerAScore = this.getFormattedValue(gameScore.playerAScore());
        String formattedPlayerBScore = this.getFormattedValue(gameScore.playerBScore());
        return "Player A : %s / Player B : %s".formatted(formattedPlayerAScore, formattedPlayerBScore);
    }

    private String getFormattedValue(int score) {
        return switch (score) {
            case 0 -> "0";
            case 1 -> "15";
            case 2 -> "30";
            case 3 -> "40";
            default -> {
                String errorMessage = ("Shouldn't reach here. Invalid score: %d. "
                                       + "Formatted score should have been 'deuce', 'advantage' or 'win'.")
                        .formatted(score);
                throw new IllegalStateException(errorMessage);
            }
        };
    }
}
