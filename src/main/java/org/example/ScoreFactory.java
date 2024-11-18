package org.example;

public class ScoreFactory {
    public Score loveAll() {
        return new Score(0, 0);
    }

    public Score thirtyForty() {
        return new Score(2, 3);
    }

    public Score fortyThirty() {
        return new Score(3, 2);
    }

    public Score deuce() {
        return new Score(3, 3);
    }

    public Score advantagePlayerA() {
        return new Score(4, 3);
    }

    public Score advantagePlayerB() {
        return new Score(3, 4);
    }

    public Score gameForty() {
        return new Score(5, 3);
    }

    public Score fortyGame() {
        return new Score(3, 5);
    }

    public Score fifteenLove() {
        return new Score(1,0);
    }

    public Score thirtyLove() {
        return new Score(2,0);
    }

    public Score gameThirty() {
        return new Score(4,2);
    }

    public Score loveFifteen() {
        return new Score(0,1);
    }

    public Score loveThirty() {
        return new Score(0,2);
    }

    public Score loveForty() {
        return new Score(0,3);
    }

    public Score gameToLovePlayerB() {
        return new Score(0,4);
    }

    public Score fortyLove() {
        return new Score(3,0);
    }
}
