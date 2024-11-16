package org.example;

import java.util.Objects;

import static org.example.PlayerScore.WIN;

public record GameScore(PlayerScore playerAScore, PlayerScore playerBScore) {

    boolean playerAWon() {
        return Objects.equals(WIN, playerAScore());
    }

    boolean playerBWon() {
        return Objects.equals(WIN, playerBScore());
    }

}
