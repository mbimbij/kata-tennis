package org.example;

public class ScorePrinter {
    public static void print(GameScore gameScore) {
        String formattedScore = getFormattedScore(gameScore);
        System.out.println(formattedScore);
    }

    private static String getFormattedScore(GameScore gameScore) {
        if(PlayerScore.WIN.equals(gameScore.playerAScore())){
            return "Player A wins the game";
        }
        if(PlayerScore.WIN.equals(gameScore.playerBScore())){
            return "Player B wins the game";
        }
        return "Player A : %s / Player B : %s".formatted(
                gameScore.playerAScore().getValue(),
                gameScore.playerBScore().getValue()
        );
    }
}
