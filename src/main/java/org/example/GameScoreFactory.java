package org.example;

public interface GameScoreFactory {
    GameScore loveAll();

    GameScore fifteenLove();

    GameScore thirtyLove();

    GameScore thirtyForty();

    GameScore fortyLove();

    GameScore loveFifteen();

    GameScore loveThirty();

    GameScore loveForty();

    GameScore fortyThirty();

    GameScore deuce();

    GameScore advantagePlayerA();

    GameScore advantagePlayerB();

    GameScore gameForty();

    GameScore gameThirty();

    GameScore gameToLovePlayerB();

    GameScore fortyGame();
}
