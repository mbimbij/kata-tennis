package org.example;

import static org.example.PlayerScore.*;

public class TestFixtures {
    public static TennisGame a30Vs40Game() {
        return new TennisGame(THIRTY, FORTY);
    }

    static GameScore deuce() {
        return new GameScore(FORTY, FORTY);
    }

    static TennisGame a40Vs40Game() {
        return new TennisGame(FORTY, FORTY);
    }

    static TennisGame advantagePlayerAGame() {
        return new TennisGame(ADVANTAGE, FORTY);
    }

    static TennisGame advantagePlayerBGame() {
        return new TennisGame(FORTY, ADVANTAGE);
    }

    static TennisGame aGameWonByPlayerA() {
        return new TennisGame(WIN, FORTY);
    }

    public static GameScore a40Vs30Score() {
        return new GameScore(FORTY, THIRTY);
    }

    public static GameScore a40Vs40Score() {
        return new GameScore(FORTY, FORTY);
    }

    public static GameScore advantagePlayerBScore() {
        return new GameScore(FORTY, ADVANTAGE);
    }

    public static GameScore advantagePlayerAScore() {
        return new GameScore(ADVANTAGE, FORTY);
    }

    public static GameScore a30Vs40Score() {
        return new GameScore(THIRTY, FORTY);
    }

    public static GameScore aWinVs40Score() {
        return new GameScore(WIN, FORTY);
    }
}
