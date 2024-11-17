package org.example;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public abstract class GameScore {
    abstract GameScore scorePointForPlayerA();

    abstract GameScore scorePointForPlayerB();

    public String format() {
        if (playerAWon()) {
            return "Player A wins the game";
        }
        if (playerBWon()) {
            return "Player B wins the game";
        }
        if (isDeuce()) {
            return "Deuce";
        }
        if (isAdvantagePlayerA()) {
            return "Advantage Player A";
        }
        if (isAdvantagePlayerB()) {
            return "Advantage Player B";
        }
        return getDefaultFormattedScore();
    }

    protected abstract String getDefaultFormattedScore();

    protected abstract boolean playerAWon();

    protected abstract boolean playerBWon();

    protected abstract boolean isDeuce();

    protected abstract boolean isAdvantagePlayerA();

    protected abstract boolean isAdvantagePlayerB();
}
