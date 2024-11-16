package org.example;

import static org.example.PlayerScore.*;

public class TennisGame {

    private PlayerScore playerAScore = ZERO;
    private PlayerScore playerBScore = ZERO;


    public GameScore scorePointForPlayerA() {
        if (ZERO.equals(playerAScore)) {
            playerAScore = FIFTEEN;
        } else if (FIFTEEN.equals(playerAScore)) {
            playerAScore = THIRTY;
        } else if (THIRTY.equals(playerAScore)) {
            playerAScore = FORTY;
        } else if (FORTY.equals(playerAScore)) {
            playerAScore = WIN;
        }
        return new GameScore(playerAScore, playerBScore);
    }

    public GameScore scorePointForPlayerB() {
        if (ZERO.equals(playerBScore)) {
            playerBScore = FIFTEEN;
        } else if (FIFTEEN.equals(playerBScore)) {
            playerBScore = THIRTY;
        } else if (THIRTY.equals(playerBScore)) {
            playerBScore = FORTY;
        } else if (FORTY.equals(playerBScore)) {
            playerBScore = WIN;
        }
        return new GameScore(playerAScore, playerBScore);
    }
}
