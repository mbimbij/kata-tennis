package org.example.scoring.core;

import lombok.With;

@With
public record SetScore(int playerAScore, int playerBScore) {
     public SetScore scorePointForPlayerA() {
        return this.withPlayerAScore(playerAScore + 1);
    }

    public SetScore scorePointForPlayerB() {
        return this.withPlayerBScore(playerBScore + 1);
    }
}
