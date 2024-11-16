package org.example;

public class ScorePrinter {
    public static void print(GameScore gameScore) {
        String formattedScore = "Player A : %s / Player B : %s".formatted(
                gameScore.playerAScore().getValue(),
                gameScore.playerBScore().getValue()
        );
        System.out.println(formattedScore);
    }
}
