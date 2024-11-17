package org.example;

import static org.example.PlayerScore.*;

public class TestFixtures {
    public static TennisGame a30_40Game() {
        return new TennisGame(THIRTY, FORTY);
    }

    static TennisGame a40_30Game() {
        return new TennisGame(FORTY, THIRTY);
    }

    static GameScore deuce() {
        return new GameScore(FORTY, FORTY);
    }

    static GameScore advantagePlayerA() {
        return new GameScore(ADVANTAGE, FORTY);
    }

    static GameScore advantagePlayerB() {
        return new GameScore(FORTY, ADVANTAGE);
    }

    static TennisGame a40_40Game() {
        return new TennisGame(FORTY, FORTY);
    }

    static TennisGame advantagePlayerAGame() {
        return new TennisGame(ADVANTAGE, FORTY);
    }

    static TennisGame advantagePlayerBGame() {
        return new TennisGame(FORTY, ADVANTAGE);
    }
}
