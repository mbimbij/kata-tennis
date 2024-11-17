package org.example;

import java.util.Objects;

import static org.example.PlayerScore.*;

public record GameScore(PlayerScore playerAScore, PlayerScore playerBScore) {

    public GameScore scorePointForPlayerA() {
        if(WIN.equals(playerAScore) || WIN.equals(playerBScore)){
            throw  new IllegalStateException("cannot score when there is a winner");
        }

        if (ZERO.equals(playerAScore)) {
            return new GameScore(FIFTEEN, playerBScore);
        } else if (FIFTEEN.equals(playerAScore)) {
            return new GameScore(THIRTY, playerBScore);
        } else if (THIRTY.equals(playerAScore)) {
            return new GameScore(FORTY, playerBScore);
        } else if (FORTY.equals(playerAScore) && FORTY.equals(playerBScore)) {
            return new GameScore(ADVANTAGE, playerBScore);
        } else if (ADVANTAGE.equals(playerBScore)) {
            return new GameScore(FORTY, FORTY);
        } else if (FORTY.equals(playerAScore)) {
            return new GameScore(WIN, playerBScore);
        } else if (ADVANTAGE.equals(playerAScore)) {
            return new GameScore(WIN, playerBScore);
        }
        throw new IllegalStateException("Should not be able to reach this");
    }

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
