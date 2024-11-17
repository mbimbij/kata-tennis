package org.example;

import static org.example.GameScore.PlayerScore.*;

public class GameScoreFactory {
    private static GameScoreFactory instance;

    private GameScoreFactory() {
    }

    public static GameScoreFactory getInstance() {
        if(instance == null) {
            instance = new GameScoreFactory();
        }
        return instance;
    }

    public GameScore loveAll() {
        return new GameScore(ZERO, ZERO);
    }

    public GameScore thirtyForty() {
        return new GameScore(THIRTY, FORTY);
    }

    public GameScore fortyThirty() {
        return new GameScore(FORTY, THIRTY);
    }

    public GameScore deuce() {
        return new GameScore(FORTY, FORTY);
    }

    public GameScore advantagePlayerA() {
        return new GameScore(ADVANTAGE, FORTY);
    }

    public GameScore advantagePlayerB() {
        return new GameScore(FORTY, ADVANTAGE);
    }

    public GameScore gameForty() {
        return new GameScore(WIN, FORTY);
    }

    public GameScore fortyGame() {
        return new GameScore(FORTY, WIN);
    }

    public GameScore fifteenLove() {
        return new GameScore(FIFTEEN, ZERO);
    }

    public GameScore thirtyLove() {
        return new GameScore(THIRTY, ZERO);
    }

    public GameScore gameThirty() {
        return new GameScore(WIN, THIRTY);
    }

    public GameScore loveFifteen() {
        return new GameScore(ZERO, FIFTEEN);
    }

    public GameScore loveThirty() {
        return new GameScore(ZERO, THIRTY);
    }

    public GameScore loveForty() {
        return new GameScore(ZERO, FORTY);
    }

    public GameScore gameToLovePlayerB() {
        return new GameScore(ZERO, WIN);
    }
}
