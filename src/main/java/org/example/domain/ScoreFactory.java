package org.example.domain;

public class ScoreFactory {
    public static Score loveAll() {
        return new Score(0, 0);
    }

    public static Score fifteenLove() {
        return new Score(1, 0);
    }

    public static Score thirtyLove() {
        return new Score(2, 0);
    }

    public static Score fortyLove() {
        return new Score(3, 0);
    }

    public static Score loveFifteen() {
        return new Score(0, 1);
    }

    public static Score loveThirty() {
        return new Score(0, 2);
    }

    public static Score loveForty() {
        return new Score(0, 3);
    }

    public static Score gameToLovePlayerB() {
        return new Score(0, 4);
    }

    public static Score thirtyForty() {
        return new Score(2, 3);
    }

    public static Score fortyThirty() {
        return new Score(3, 2);
    }

    public static Score deuce() {
        return new Score(3, 3);
    }

    public static Score advantagePlayerA() {
        return new Score(4, 3);
    }

    public static Score advantagePlayerB() {
        return new Score(3, 4);
    }

    public static Score gameFortyPlayerA() {
        return new Score(5, 3);
    }

    public static Score gameFortyPlayerB() {
        return new Score(3, 5);
    }

    public static Score gameThirtyPlayerA() {
        return new Score(4, 2);
    }
}
