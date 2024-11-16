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
        } else if (FORTY.equals(playerBScore)) {
            playerAScore = PLAYER_DEUCE;
            playerBScore = PLAYER_DEUCE;
        } else if (THIRTY.equals(playerAScore)) {
            playerAScore = FORTY;
        } else if (FORTY.equals(playerAScore)) {
            playerAScore = WIN;
        } else if (PLAYER_DEUCE.equals(playerAScore) && PLAYER_DEUCE.equals(playerBScore)) {
            playerAScore = ADVANTAGE;
        }
        return new GameScore(playerAScore, playerBScore);
    }

    public GameScore scorePointForPlayerB() {
        if (ZERO.equals(playerBScore)) {
            playerBScore = FIFTEEN;
        } else if (FIFTEEN.equals(playerBScore)) {
            playerBScore = THIRTY;
        } else if (FORTY.equals(playerAScore)) {
            playerAScore = PLAYER_DEUCE;
            playerBScore = PLAYER_DEUCE;
        } else if (THIRTY.equals(playerBScore)) {
            playerBScore = FORTY;
        } else if (FORTY.equals(playerBScore)) {
            playerBScore = WIN;
        } else if (PLAYER_DEUCE.equals(playerAScore) && PLAYER_DEUCE.equals(playerBScore)) {
            playerBScore = ADVANTAGE;
        }
        return new GameScore(playerAScore, playerBScore);
    }
}
