package org.example.countscorerestapi;

import org.example.shared.Score;

public record ScoreDto(String playerAScore, String playerBScore) {
    public static ScoreDto fromDomain(Score score) {
        if (score.isAdvantagePlayerA()) {
            return new ScoreDto("Ad", "40");
        } else if (score.isAdvantagePlayerB()) {
            return new ScoreDto("40", "Ad");
        } else if (score.isDeuce()) {
            return new ScoreDto("40", "40");
        }else {
            return new ScoreDto(format(score.playerAScore()), format(score.playerBScore()));
        }
    }

    private static String format(int score) {
        if (score == 0) {
            return "0";
        } else if (score == 1) {
            return "15";
        } else if (score == 2) {
            return "30";
        } else if (score == 3) {
            return "40";
        } else if (score >= 4) {
            return "Game";
        }
        throw new IllegalArgumentException("Should never reach this. Score should have been formatted as deuce, adv, etc.");
    }
}
