package org.example.cli;

import org.example.core.Score;

public class ConsoleScoreFormatter {
    public ConsoleScoreFormatter() {
    }

    public String format(Score gameScore) {
        if (gameScore.playerAWon()) {
            return "Player A wins the game";
        } else if (gameScore.playerBWon()) {
            return "Player B wins the game";
        } else if (gameScore.isDeuce()) {
            return "Deuce";
        } else if (gameScore.isAdvantagePlayerA()) {
            return "Advantage Player A";
        } else if (gameScore.isAdvantagePlayerB()) {
            return "Advantage Player B";
        } else {
            return "Player A : %s / Player B : %s".formatted(gameScore.getPlayerAScore().getValue(),
                    gameScore.getPlayerBScore().getValue());
        }
    }
}
