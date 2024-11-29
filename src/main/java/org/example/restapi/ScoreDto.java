package org.example.restapi;

import org.example.core.PlayerScore;
import org.example.core.Score;

public record ScoreDto(String playerAScore, String playerBScore) {
    public static ScoreDto fromDomain(Score score) {
        PlayerScore playerAScore = score.getPlayerBScore();
        PlayerScore playerBScore = score.getPlayerAScore();
        return new ScoreDto(playerBScore.getValue(), playerAScore.getValue());
    }
}
