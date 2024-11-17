package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

import static org.example.EnumBasedGameScore.PlayerScore.*;


@EqualsAndHashCode(callSuper = true)
@ToString
@Getter
public final class EnumBasedGameScore extends GameScore {
    private final PlayerScore playerAScore;
    private final PlayerScore playerBScore;

    public EnumBasedGameScore(PlayerScore playerAScore, PlayerScore playerBScore) {
        this.playerAScore = playerAScore;
        this.playerBScore = playerBScore;
    }

    @Override
    public GameScore scorePointForPlayerA() {
        if (WIN.equals(playerAScore) || WIN.equals(playerBScore)) {
            throw new IllegalStateException("cannot score when there is a winner");
        }

        if (ZERO.equals(playerAScore)) {
            return new EnumBasedGameScore(FIFTEEN, playerBScore);
        } else if (FIFTEEN.equals(playerAScore)) {
            return new EnumBasedGameScore(THIRTY, playerBScore);
        } else if (THIRTY.equals(playerAScore)) {
            return new EnumBasedGameScore(FORTY, playerBScore);
        } else if (FORTY.equals(playerAScore) && FORTY.equals(playerBScore)) {
            return new EnumBasedGameScore(ADVANTAGE, playerBScore);
        } else if (ADVANTAGE.equals(playerBScore)) {
            return new EnumBasedGameScore(FORTY, FORTY);
        } else if (FORTY.equals(playerAScore) || ADVANTAGE.equals(playerAScore)) {
            return new EnumBasedGameScore(WIN, playerBScore);
        }
        throw new IllegalStateException("Should not be able to reach this");
    }

    @Override
    public GameScore scorePointForPlayerB() {
        if (WIN.equals(playerAScore) || WIN.equals(playerBScore)) {
            throw new IllegalStateException("cannot score when there is a winner");
        }

        if (ZERO.equals(playerBScore)) {
            return new EnumBasedGameScore(playerAScore, FIFTEEN);
        } else if (FIFTEEN.equals(playerBScore)) {
            return new EnumBasedGameScore(playerAScore, THIRTY);
        } else if (THIRTY.equals(playerBScore)) {
            return new EnumBasedGameScore(playerAScore, FORTY);
        } else if (FORTY.equals(playerAScore) && FORTY.equals(playerBScore)) {
            return new EnumBasedGameScore(playerAScore, ADVANTAGE);
        } else if (ADVANTAGE.equals(playerAScore)) {
            return new EnumBasedGameScore(FORTY, FORTY);
        } else if (FORTY.equals(playerBScore)) {
            return new EnumBasedGameScore(playerAScore, WIN);
        } else if (ADVANTAGE.equals(playerBScore)) {
            return new EnumBasedGameScore(playerAScore, WIN);
        }
        return new EnumBasedGameScore(playerAScore, playerBScore);
    }

    @Override
    public String getDefaultFormattedScore() {
        return "Player A : %s / Player B : %s".formatted(
                getStringValue(playerAScore()),
                getStringValue(playerBScore()));
    }

    @Override
    public boolean playerAWon() {
        return Objects.equals(WIN, playerAScore());
    }

    @Override
    public boolean playerBWon() {
        return Objects.equals(WIN, playerBScore());
    }

    @Override
    public boolean isDeuce() {
        return this.equals(new EnumBasedGameScore(FORTY, FORTY));
    }

    @Override
    public boolean isAdvantagePlayerA() {
        return this.equals(new EnumBasedGameScore(ADVANTAGE, FORTY));
    }

    @Override
    public boolean isAdvantagePlayerB() {
        return this.equals(new EnumBasedGameScore(FORTY, ADVANTAGE));
    }

    private String getStringValue(PlayerScore playerScore) {
        return switch (playerScore) {
            case ZERO -> "0";
            case FIFTEEN -> "15";
            case THIRTY -> "30";
            case FORTY -> "40";
            default -> throw new IllegalStateException("should not try to get string representation for single player score: " + playerScore);
        };
    }

    private PlayerScore playerAScore() {
        return playerAScore;
    }

    private PlayerScore playerBScore() {
        return playerBScore;
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
