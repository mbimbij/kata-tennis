package org.example;

public class TennisGame {

    private PlayerScore playerAScore = PlayerScore.ZERO;
    private PlayerScore playerBScore = PlayerScore.ZERO;


    public GameScore scorePointForPlayerA() {
        if (PlayerScore.ZERO.equals(playerAScore)) {
            playerAScore = PlayerScore.FIFTEEN;
        } else if (PlayerScore.FIFTEEN.equals(playerAScore)) {
            playerAScore = PlayerScore.THIRTY;
        }
        return new GameScore(playerAScore, playerBScore);
    }

    public GameScore scorePointForPlayerB() {
        if (PlayerScore.ZERO.equals(playerBScore)) {
            playerBScore = PlayerScore.FIFTEEN;
        } else if (PlayerScore.FIFTEEN.equals(playerBScore)) {
            playerBScore = PlayerScore.THIRTY;
        }
        return new GameScore(playerAScore, playerBScore);
    }
}
