package org.example.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.shared.core.PlayerScore;
import org.example.shared.core.Score;

public record ScoreDto(
        @JsonProperty("A")
        String playerAScore,
        @JsonProperty("B")
        String playerBScore) {
    public static ScoreDto fromDomain(Score score) {
        PlayerScore playerAScore = score.getPlayerBScore();
        PlayerScore playerBScore = score.getPlayerAScore();
        return new ScoreDto(playerBScore.getValue(), playerAScore.getValue());
    }
}
