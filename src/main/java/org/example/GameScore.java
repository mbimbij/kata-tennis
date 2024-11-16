package org.example;

import java.util.Objects;

import static org.example.PlayerScore.*;

public record GameScore(PlayerScore playerAScore, PlayerScore playerBScore) {

    boolean playerAWon() {
        return Objects.equals(WIN, playerAScore());
    }

    boolean playerBWon() {
        return Objects.equals(WIN, playerBScore());
    }

    boolean isDeuce() {
        return this.equals(new GameScore(FORTY, FORTY));
    }

    boolean isAdvantagePlayerA() {
        return this.equals(new GameScore(ADVANTAGE, FORTY));
    }

    boolean isAdvantagePlayerB() {
        return this.equals(new GameScore(FORTY, ADVANTAGE));
    }
}
