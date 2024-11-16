package org.example;


import static org.example.PlayerScore.ADVANTAGE;
import static org.example.PlayerScore.PLAYER_DEUCE;

public class GameScores {
    public static final GameScore DEUCE = new GameScore(PLAYER_DEUCE, PLAYER_DEUCE);
    public static final GameScore ADVANTAGE_PLAYER_A = new GameScore(ADVANTAGE, PLAYER_DEUCE);
    public static final GameScore ADVANTAGE_PLAYER_B = new GameScore(PLAYER_DEUCE, ADVANTAGE);
}
