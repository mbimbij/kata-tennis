package org.example;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Objects;

import static org.example.EnumBasedGameScore.PlayerScore.*;


@EqualsAndHashCode(callSuper = false)
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
        validateNoWinner();

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
            return new EnumBasedGameScore(GAME, playerBScore);
        }
        throw new IllegalStateException("Should not be able to reach this");
    }

    @Override
    public GameScore scorePointForPlayerB() {
        validateNoWinner();

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
            return new EnumBasedGameScore(playerAScore, GAME);
        } else if (ADVANTAGE.equals(playerBScore)) {
            return new EnumBasedGameScore(playerAScore, GAME);
        }
        return new EnumBasedGameScore(playerAScore, playerBScore);
    }

    private void validateNoWinner() {
        if (GAME.equals(playerAScore) || GAME.equals(playerBScore)) {
            throw new IllegalStateException("Cannot score when there is a winner");
        }
    }

    @Override
    public String getDefaultFormattedScore() {
        return "Player A : %s / Player B : %s".formatted(
                getStringValue(playerAScore()),
                getStringValue(playerBScore()));
    }

    @Override
    public boolean playerAWon() {
        return Objects.equals(GAME, playerAScore());
    }

    @Override
    public boolean playerBWon() {
        return Objects.equals(GAME, playerBScore());
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

    @Override
    protected boolean scoreDetailsEqual(GameScore o) {
        EnumBasedGameScore that = ((EnumBasedGameScore) o);
        return playerAScore == that.playerAScore && playerBScore == that.playerBScore;
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
        GAME
    }
}
