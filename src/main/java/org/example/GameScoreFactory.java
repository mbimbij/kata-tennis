package org.example;

import static org.example.EnumBasedGameScore.PlayerScore.*;

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
        return new EnumBasedGameScore(ZERO, ZERO);
    }

    public GameScore thirtyForty() {
        return new EnumBasedGameScore(THIRTY, FORTY);
    }

    public GameScore fortyThirty() {
        return new EnumBasedGameScore(FORTY, THIRTY);
    }

    public GameScore deuce() {
        return new EnumBasedGameScore(FORTY, FORTY);
    }

    public GameScore advantagePlayerA() {
        return new EnumBasedGameScore(ADVANTAGE, FORTY);
    }

    public GameScore advantagePlayerB() {
        return new EnumBasedGameScore(FORTY, ADVANTAGE);
    }

    public GameScore gameForty() {
        return new EnumBasedGameScore(GAME, FORTY);
    }

    public GameScore fortyGame() {
        return new EnumBasedGameScore(FORTY, GAME);
    }

    public GameScore fifteenLove() {
        return new EnumBasedGameScore(FIFTEEN, ZERO);
    }

    public GameScore thirtyLove() {
        return new EnumBasedGameScore(THIRTY, ZERO);
    }

    public GameScore gameThirty() {
        return new EnumBasedGameScore(GAME, THIRTY);
    }

    public GameScore loveFifteen() {
        return new EnumBasedGameScore(ZERO, FIFTEEN);
    }

    public GameScore loveThirty() {
        return new EnumBasedGameScore(ZERO, THIRTY);
    }

    public GameScore loveForty() {
        return new EnumBasedGameScore(ZERO, FORTY);
    }

    public GameScore gameToLovePlayerB() {
        return new EnumBasedGameScore(ZERO, GAME);
    }
}
