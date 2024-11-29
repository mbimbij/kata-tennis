package org.example.countscorerestapi;

import org.example.shared.Score;

public record ScoreDto(String playerAScore, String playerBScore) {
    public static ScoreDto fromDomain(Score score) {
        return switch (score){
            case Score s when s.isAdvantagePlayerA() -> new ScoreDto("Ad", "40");
            case Score s when s.isAdvantagePlayerB() -> new ScoreDto("40", "Ad");
            case Score s when s.isDeuce() -> new ScoreDto("40", "40");
            default ->new ScoreDto(format(score.playerAScore()), format(score.playerBScore()));
        };
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
