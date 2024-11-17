package org.example;

import static org.example.PlayerScore.*;

public class TestFixtures {
    static GameScore deuce() {
        return new GameScore(FORTY, FORTY);
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

    public static GameScore a40VsWinScore() {
        return new GameScore(FORTY, WIN);
    }
}
