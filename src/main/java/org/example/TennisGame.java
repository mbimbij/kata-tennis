package org.example;

public class TennisGame {

    private PlayerScore playerAScore = PlayerScore.ZERO;
    private PlayerScore playerBScore = PlayerScore.ZERO;


    public GameScore scorePointForPlayerA() {
        if(PlayerScore.ZERO.equals(playerAScore)){
            playerAScore = PlayerScore.FIFTEEN;
        }
        return new GameScore(playerAScore, playerBScore);
    }

    public GameScore scorePointForPlayerB() {
        if(PlayerScore.ZERO.equals(playerBScore)){
            playerBScore = PlayerScore.FIFTEEN;
        }
        return new GameScore(playerAScore, playerBScore);
    }
}
