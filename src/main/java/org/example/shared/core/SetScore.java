package org.example.shared.core;

import lombok.With;

@With
public record SetScore(int playerAScore, int playerBScore) {
     public SetScore scorePointForPlayerA() {
        return this.withPlayerAScore(playerAScore + 1);
    }
}
