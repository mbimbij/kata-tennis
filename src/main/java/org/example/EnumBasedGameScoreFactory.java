package org.example;

import static org.example.EnumBasedGameScore.PlayerScore.*;

public class EnumBasedGameScoreFactory implements GameScoreFactory {

    public EnumBasedGameScoreFactory() {
    }

    @Override
    public GameScore loveAll() {
        return new EnumBasedGameScore(ZERO, ZERO);
    }

    @Override
    public GameScore thirtyForty() {
        return new EnumBasedGameScore(THIRTY, FORTY);
    }

    @Override
    public GameScore fortyThirty() {
        return new EnumBasedGameScore(FORTY, THIRTY);
    }

    @Override
    public GameScore deuce() {
        return new EnumBasedGameScore(FORTY, FORTY);
    }

    @Override
    public GameScore advantagePlayerA() {
        return new EnumBasedGameScore(ADVANTAGE, FORTY);
    }

    @Override
    public GameScore advantagePlayerB() {
        return new EnumBasedGameScore(FORTY, ADVANTAGE);
    }

    @Override
    public GameScore gameForty() {
        return new EnumBasedGameScore(GAME, FORTY);
    }

    @Override
    public GameScore fortyGame() {
        return new EnumBasedGameScore(FORTY, GAME);
    }

    @Override
    public GameScore fifteenLove() {
        return new EnumBasedGameScore(FIFTEEN, ZERO);
    }

    @Override
    public GameScore thirtyLove() {
        return new EnumBasedGameScore(THIRTY, ZERO);
    }

    @Override
    public GameScore gameThirty() {
        return new EnumBasedGameScore(GAME, THIRTY);
    }

    @Override
    public GameScore loveFifteen() {
        return new EnumBasedGameScore(ZERO, FIFTEEN);
    }

    @Override
    public GameScore loveThirty() {
        return new EnumBasedGameScore(ZERO, THIRTY);
    }

    @Override
    public GameScore loveForty() {
        return new EnumBasedGameScore(ZERO, FORTY);
    }

    @Override
    public GameScore gameToLovePlayerB() {
        return new EnumBasedGameScore(ZERO, GAME);
    }

    @Override
    public GameScore fortyLove() {
        return new EnumBasedGameScore(FORTY, ZERO);
    }
}
