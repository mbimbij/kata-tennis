package org.example;

public class IntegerBasedGameScoreFactory implements GameScoreFactory {
    @Override
    public GameScore loveAll() {
        return new IntegerBasedGameScore(0, 0);
    }

    @Override
    public GameScore thirtyForty() {
        return new IntegerBasedGameScore(2, 3);
    }

    @Override
    public GameScore fortyThirty() {
        return new IntegerBasedGameScore(3, 2);
    }

    @Override
    public GameScore deuce() {
        return new IntegerBasedGameScore(3, 3);
    }

    @Override
    public GameScore advantagePlayerA() {
        return new IntegerBasedGameScore(4, 3);
    }

    @Override
    public GameScore advantagePlayerB() {
        return new IntegerBasedGameScore(3, 4);
    }

    @Override
    public GameScore gameForty() {
        return new IntegerBasedGameScore(5, 3);
    }

    @Override
    public GameScore fortyGame() {
        return new IntegerBasedGameScore(3, 5);
    }

    @Override
    public GameScore fifteenLove() {
        return new IntegerBasedGameScore(1,0);
    }

    @Override
    public GameScore thirtyLove() {
        return new IntegerBasedGameScore(2,0);
    }

    @Override
    public GameScore gameThirty() {
        return new IntegerBasedGameScore(4,2);
    }

    @Override
    public GameScore loveFifteen() {
        return new IntegerBasedGameScore(0,1);
    }

    @Override
    public GameScore loveThirty() {
        return new IntegerBasedGameScore(0,2);
    }

    @Override
    public GameScore loveForty() {
        return new IntegerBasedGameScore(0,3);
    }

    @Override
    public GameScore gameToLovePlayerB() {
        return new IntegerBasedGameScore(0,4);
    }

    @Override
    public GameScore fortyLove() {
        return new IntegerBasedGameScore(3,0);
    }
}
