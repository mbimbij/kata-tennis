package org.example;

public class Main {
    public static void main(String[] args) {
        Score initialScore = Scores.loveAll();
        ScoreFormatter scoreFormatter = new ScoreFormatter();
        TennisApplication tennisApplication = new TennisApplication(initialScore, scoreFormatter);
        String playsSequence = "ABABAA";
        tennisApplication.playGameForInput(playsSequence);
    }
}
