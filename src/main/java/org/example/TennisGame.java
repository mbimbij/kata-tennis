package org.example;

public class TennisGame {

    private PlayerScore playerAScore = PlayerScore.ZERO;
    private PlayerScore playerBScore = PlayerScore.ZERO;


    void scorePointForPlayerA() {
        if(PlayerScore.ZERO.equals(playerAScore)){
            playerAScore = PlayerScore.FIFTEEN;
        }
        printScore();
    }

    private void printScore() {
        String formattedScore = "Player A : %s / Player B : %s".formatted(playerAScore.getValue(), playerBScore.getValue());
        System.out.println(formattedScore);
    }

    public void scorePointForPlayerB() {
        if(PlayerScore.ZERO.equals(playerBScore)){
            playerBScore = PlayerScore.FIFTEEN;
        }
        printScore();
    }
}
