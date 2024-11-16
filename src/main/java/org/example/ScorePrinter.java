package org.example;

import static org.example.GameScores.*;

public class ScorePrinter {
    public static void print(GameScore gameScore) {
        String formattedScore = getFormattedScore(gameScore);
        System.out.println(formattedScore);
    }

    private static String getFormattedScore(GameScore gameScore) {
        if(gameScore.playerAWon()){
            return "Player A wins the game";
        }
        if(gameScore.playerBWon()){
            return "Player B wins the game";
        }
        if(gameScore.equals(DEUCE)){
            return "Deuce";
        }
        if(gameScore.equals(ADVANTAGE_PLAYER_A)){
            return "Advantage Player A";
        }
        if(gameScore.equals(ADVANTAGE_PLAYER_B)){
            return "Advantage Player B";
        }
        return "Player A : %s / Player B : %s".formatted(
                getStringValue(gameScore.playerAScore()),
                getStringValue(gameScore.playerBScore())
        );
    }

    private static String getStringValue(PlayerScore playerScore) {
        return switch (playerScore){
            case ZERO -> "0";
            case FIFTEEN -> "15";
            case THIRTY -> "30";
            case FORTY -> "40";
            default -> throw new IllegalStateException("should not try to get string representation for single player score: " + playerScore);
        };
    }

}
