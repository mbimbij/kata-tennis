package org.example;

public class Main {
    public static void main(String[] args) {
        GameScoreFactory gameScoreFactory = new EnumBasedGameScoreFactory();
        GameScore initialScore = gameScoreFactory.loveAll();
        new TennisApplication(initialScore).playGameForInput("ABABAA");
    }
}
