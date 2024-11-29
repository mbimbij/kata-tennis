package org.example.countscorerestapi;

import org.example.shared.Score;

public record ScoreDto (String playerAScore, String playerBScore) {
    public static ScoreDto fromDomain(Score score) {
        return new ScoreDto(String.valueOf(score.playerAScore()), String.valueOf(score.playerBScore()));
    }
}
