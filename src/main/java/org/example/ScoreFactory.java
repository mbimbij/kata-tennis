package org.example;

public class ScoreFactory {
    public GameScore loveAll() {
        return new GameScore(0, 0);
    }

    public GameScore thirtyForty() {
        return new GameScore(2, 3);
    }

    public GameScore fortyThirty() {
        return new GameScore(3, 2);
    }

    public GameScore deuce() {
        return new GameScore(3, 3);
    }

    public GameScore advantagePlayerA() {
        return new GameScore(4, 3);
    }

    public GameScore advantagePlayerB() {
        return new GameScore(3, 4);
    }

    public GameScore gameForty() {
        return new GameScore(5, 3);
    }

    public GameScore fortyGame() {
        return new GameScore(3, 5);
    }

    public GameScore fifteenLove() {
        return new GameScore(1,0);
    }

    public GameScore thirtyLove() {
        return new GameScore(2,0);
    }

    public GameScore gameThirty() {
        return new GameScore(4,2);
    }

    public GameScore loveFifteen() {
        return new GameScore(0,1);
    }

    public GameScore loveThirty() {
        return new GameScore(0,2);
    }

    public GameScore loveForty() {
        return new GameScore(0,3);
    }

    public GameScore gameToLovePlayerB() {
        return new GameScore(0,4);
    }

    public GameScore fortyLove() {
        return new GameScore(3,0);
    }
}
