package org.example;

import java.util.Objects;

import static org.example.PlayerScore.*;

public record GameScore(PlayerScore playerAScore, PlayerScore playerBScore) {

    public static GameScore loveAll(){
        return new GameScore(ZERO, ZERO);
    }

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
        } else if (FORTY.equals(playerAScore) || ADVANTAGE.equals(playerAScore)) {
            return new GameScore(WIN, playerBScore);
        }
        throw new IllegalStateException("Should not be able to reach this");
    }

    public GameScore scorePointForPlayerB() {
        if(WIN.equals(playerAScore) || WIN.equals(playerBScore)){
            throw  new IllegalStateException("cannot score when there is a winner");
        }

        if (ZERO.equals(playerBScore)) {
            return new GameScore(playerAScore, FIFTEEN);
        } else if (FIFTEEN.equals(playerBScore)) {
            return new GameScore(playerAScore, THIRTY);
        } else if (THIRTY.equals(playerBScore)) {
            return new GameScore(playerAScore, FORTY);
        } else if (FORTY.equals(playerAScore) && FORTY.equals(playerBScore)) {
            return new GameScore(playerAScore, ADVANTAGE);
        } else if (ADVANTAGE.equals(playerAScore)) {
            return new GameScore(FORTY, FORTY);
        } else if (FORTY.equals(playerBScore)) {
            return new GameScore(playerAScore, WIN);
        } else if (ADVANTAGE.equals(playerBScore)) {
            return new GameScore(playerAScore, WIN);
        }
        return new GameScore(playerAScore, playerBScore);
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
