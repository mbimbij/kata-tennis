package org.example;

public class Main {
    public static void main(String[] args) {
        GameScoreFactory gameScoreFactory = new IntegerBasedGameScoreFactory();
        GameScore initialScore = gameScoreFactory.loveAll();
        ScoreFormatter scoreFormatter = new ScoreFormatter();
        new TennisApplication(initialScore, scoreFormatter).playGameForInput("ABABAA");
    }
}
