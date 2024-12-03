package org.example.scoring.infra;


import org.example.scoring.core.GameScore;

public class ConsoleScoreFormatter {
    public ConsoleScoreFormatter() {
    }

    public String format(GameScore gameScore) {
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
