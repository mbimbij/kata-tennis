package org.example;

import static org.example.PlayerScore.*;

public class TennisGame {

    private PlayerScore playerAScore;
    private PlayerScore playerBScore;

    public TennisGame() {
        playerAScore = ZERO;
        playerBScore = ZERO;
    }

    public TennisGame(PlayerScore playerAScore, PlayerScore playerBScore) {
        this.playerAScore = playerAScore;
        this.playerBScore = playerBScore;
    }

    public GameScore scorePointForPlayerA() {
        if (ZERO.equals(playerAScore)) {
            playerAScore = FIFTEEN;
        } else if (FIFTEEN.equals(playerAScore)) {
            playerAScore = THIRTY;
        } else if (THIRTY.equals(playerAScore)) {
            playerAScore = FORTY;
        } else if (FORTY.equals(playerAScore) && FORTY.equals(playerBScore)) {
            playerAScore = ADVANTAGE;
        } else if (ADVANTAGE.equals(playerBScore)) {
            playerAScore = FORTY;
            playerBScore = FORTY;
        } else if (FORTY.equals(playerAScore)) {
            playerAScore = WIN;
        } else if (ADVANTAGE.equals(playerAScore)) {
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
        } else if (FORTY.equals(playerAScore) && FORTY.equals(playerBScore)) {
            playerBScore = ADVANTAGE;
        } else if (ADVANTAGE.equals(playerAScore)) {
            playerAScore = FORTY;
            playerBScore = FORTY;
        } else if (FORTY.equals(playerBScore)) {
            playerBScore = WIN;
        }
        return new GameScore(playerAScore, playerBScore);
    }
}
