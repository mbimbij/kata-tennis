package org.example.shared.core;

import lombok.Getter;

@Getter
public enum PlayerGameScore {
    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("Ad"),
    GAME("Game"),
    ;

    private final String value;

    PlayerGameScore(String value) {
        this.value = value;
    }

    public static PlayerGameScore fromValue(String value) {
        for (PlayerGameScore score : values()) {
            if (score.value.equals(value)) {
                return score;
            }
        }
        throw new IllegalArgumentException("Invalid PlayerScore value: " + value);
    }
}
