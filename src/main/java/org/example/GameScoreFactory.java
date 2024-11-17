package org.example;

import static org.example.GameScore.PlayerScore.*;

public class GameScoreFactory {
    public static GameScore loveAll() {
        return new GameScore(ZERO, ZERO);
    }

    public static GameScore thirtyForty() {
        return new GameScore(THIRTY, FORTY);
    }

    public static GameScore fortyThirty() {
        return new GameScore(FORTY, THIRTY);
    }

    public static GameScore deuce() {
        return new GameScore(FORTY, FORTY);
    }

    public static GameScore advantagePlayerA() {
        return new GameScore(ADVANTAGE, FORTY);
    }

    public static GameScore advantagePlayerB() {
        return new GameScore(FORTY, ADVANTAGE);
    }

    public static GameScore gameForty() {
        return new GameScore(WIN, FORTY);
    }

    public static GameScore fortyGame() {
        return new GameScore(FORTY, WIN);
    }

    public static GameScore fifteenLove() {
        return new GameScore(FIFTEEN, ZERO);
    }

    public static GameScore thirtyLove() {
        return new GameScore(THIRTY, ZERO);
    }

    public static GameScore gameThirty() {
        return new GameScore(WIN, THIRTY);
    }

    public static GameScore loveFifteen() {
        return new GameScore(ZERO, FIFTEEN);
    }

    public static GameScore loveThirty() {
        return new GameScore(ZERO, THIRTY);
    }

    public static GameScore loveForty() {
        return new GameScore(ZERO, FORTY);
    }

    public static GameScore gameToLovePlayerB() {
        return new GameScore(ZERO, WIN);
    }
}
