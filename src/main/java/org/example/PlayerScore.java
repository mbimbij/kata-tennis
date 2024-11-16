package org.example;

public enum PlayerScore {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    DEUCE("DEUCE"),
    ADVANTAGE("ADVANTAGE"),
    WIN("WIN");

    private final String value;

    PlayerScore(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
