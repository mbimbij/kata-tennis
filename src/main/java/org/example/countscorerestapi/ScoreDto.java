package org.example.countscorerestapi;

import org.example.shared.PlayerScore;
import org.example.shared.Score;

public record ScoreDto(String playerAScore, String playerBScore) {
    public static ScoreDto fromDomain(Score score) {
        PlayerScore playerAScore = score.getPlayerBScore();
        PlayerScore playerBScore = score.getPlayerAScore();
        return new ScoreDto(playerBScore.getValue(), playerAScore.getValue());
    }
}
