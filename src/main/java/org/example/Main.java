package org.example;

public class Main {
    public static void main(String[] args) {
        ScoreFactory gameScoreFactory = new ScoreFactory();
        GameScore initialScore = gameScoreFactory.loveAll();
        ScoreFormatter scoreFormatter = new ScoreFormatter();
        new TennisApplication(initialScore, scoreFormatter).playGameForInput("ABABAA");
    }
}
