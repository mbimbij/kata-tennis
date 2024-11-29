package org.example.shared;

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
}
