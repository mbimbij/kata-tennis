package org.example.restapi;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.example.core.PlayerScore;
import org.example.core.Score;

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
