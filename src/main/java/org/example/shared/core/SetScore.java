package org.example.shared.core;

import lombok.With;

@With
record SetScore(int playerAScore, int playerBScore) {
    SetScore scorePointForPlayerA() {
        return this.withPlayerAScore(playerAScore + 1);
    }
}
