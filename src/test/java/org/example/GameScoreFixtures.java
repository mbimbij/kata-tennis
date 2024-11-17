package org.example;

import static org.example.GameScore.PlayerScore.*;

class GameScoreFixtures {
    static GameScore thirtyForty() {
        return new GameScore(THIRTY, FORTY);
    }

    static GameScore fortyThirty() {
        return new GameScore(FORTY, THIRTY);
    }

    static GameScore deuce() {
        return new GameScore(FORTY, FORTY);
    }

    static GameScore advantagePlayerA() {
        return new GameScore(ADVANTAGE, FORTY);
    }

    static GameScore advantagePlayerB() {
        return new GameScore(FORTY, ADVANTAGE);
    }

    static GameScore gameForty() {
        return new GameScore(WIN, FORTY);
    }

    static GameScore fortyGame() {
        return new GameScore(FORTY, WIN);
    }

    static GameScore fifteenLove() {
        return new GameScore(FIFTEEN, ZERO);
    }

    static GameScore thirtyLove() {
        return new GameScore(THIRTY, ZERO);
    }

    static GameScore gameThirty() {
        return new GameScore(WIN, THIRTY);
    }

    static GameScore loveFifteen() {
        return new GameScore(ZERO, FIFTEEN);
    }

    static GameScore loveThirty() {
        return new GameScore(ZERO, THIRTY);
    }

    static GameScore loveForty() {
        return new GameScore(ZERO, FORTY);
    }

    static GameScore gameToLovePlayerB() {
        return new GameScore(ZERO, WIN);
    }
}
