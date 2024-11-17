package org.example;

public class Main {
    public static void main(String[] args) {
        GameScoreFactory gameScoreFactory = GameScoreFactory.getInstance();
        GameScore initialScore = gameScoreFactory.loveAll();
        new TennisApplication(initialScore).playGameForInput("ABABAA");
    }
}
