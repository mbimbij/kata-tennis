package org.example;

import java.util.Objects;

import static org.example.GameScores.*;
import static org.example.PlayerScore.*;

public class ScorePrinter {
    public static void print(GameScore gameScore) {
        String formattedScore = getFormattedScore(gameScore);
        System.out.println(formattedScore);
    }

    private static String getFormattedScore(GameScore gameScore) {
        if(playerAWon(gameScore)){
            return "Player A wins the game";
        }
        if(playerBWon(gameScore)){
            return "Player B wins the game";
        }
        if(Objects.equals(DEUCE, gameScore)){
            return "Deuce";
        }
        if(Objects.equals(ADVANTAGE_PLAYER_A, gameScore)){
            return "Advantage Player A";
        }
        if(Objects.equals(ADVANTAGE_PLAYER_B, gameScore)){
            return "Advantage Player B";
        }
        return "Player A : %s / Player B : %s".formatted(
                gameScore.playerAScore().getValue(),
                gameScore.playerBScore().getValue()
        );
    }

    private static boolean playerBWon(GameScore gameScore) {
        return WIN.equals(gameScore.playerBScore());
    }

    private static boolean playerAWon(GameScore gameScore) {
        return WIN.equals(gameScore.playerAScore());
    }
}
