package org.example.shared.core;

import lombok.Getter;

@Getter
public enum PlayerScore {
    LOVE("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("Ad"),
    GAME("Game"),
    ;

    private final String value;

    PlayerScore(String value) {
        this.value = value;
    }

    public static PlayerScore fromValue(String value) {
        for (PlayerScore score : values()) {
            if (score.value.equals(value)) {
                return score;
            }
        }
        throw new IllegalArgumentException("Invalid PlayerScore value: " + value);
    }
}
