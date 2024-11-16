package org.example;

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
        if(gameScore.isDeuce()){
            return "Deuce";
        }
        if(gameScore.isAdvantagePlayerA()){
            return "Advantage Player A";
        }
        if(gameScore.isAdvantagePlayerB()){
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
