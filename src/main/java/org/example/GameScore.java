package org.example;

import java.util.Objects;

import static org.example.GameScore.PlayerScore.*;

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

    public String format() {
        if(playerAWon()){
            return "Player A wins the game";
        }
        if(playerBWon()){
            return "Player B wins the game";
        }
        if(isDeuce()){
            return "Deuce";
        }
        if(isAdvantagePlayerA()){
            return "Advantage Player A";
        }
        if(isAdvantagePlayerB()){
            return "Advantage Player B";
        }
        return "Player A : %s / Player B : %s".formatted(
                getStringValue(playerAScore()),
                getStringValue(playerBScore())
        );
    }

    private boolean playerAWon() {
        return Objects.equals(WIN, playerAScore());
    }

    private boolean playerBWon() {
        return Objects.equals(WIN, playerBScore());
    }

    private boolean isDeuce() {
        return this.equals(new GameScore(FORTY, FORTY));
    }

    private boolean isAdvantagePlayerA() {
        return this.equals(new GameScore(ADVANTAGE, FORTY));
    }

    private boolean isAdvantagePlayerB() {
        return this.equals(new GameScore(FORTY, ADVANTAGE));
    }

    private String getStringValue(PlayerScore playerScore) {
        return switch (playerScore){
            case ZERO -> "0";
            case FIFTEEN -> "15";
            case THIRTY -> "30";
            case FORTY -> "40";
            default -> throw new IllegalStateException("should not try to get string representation for single player score: " + playerScore);
        };
    }

    public enum PlayerScore {
        ZERO,
        FIFTEEN,
        THIRTY,
        FORTY,
        ADVANTAGE,
        WIN
    }
}
