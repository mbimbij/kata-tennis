package org.example.rest;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.shared.core.PlayerGameScore;
import org.example.shared.core.GameScore;

public record ScoreDto(
        @JsonProperty("A")
        String playerAScore,
        @JsonProperty("B")
        String playerBScore) {
    public static ScoreDto fromDomain(GameScore score) {
        PlayerGameScore playerAScore = score.getPlayerBScore();
        PlayerGameScore playerBScore = score.getPlayerAScore();
        return new ScoreDto(playerBScore.getValue(), playerAScore.getValue());
    }
}
