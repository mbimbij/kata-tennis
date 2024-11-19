package org.example;

public class Main {
    public static void main(String[] args) {
        Score initialScore = Scores.loveAll();
        TennisApplication tennisApplication = new TennisApplication(initialScore);
        String playsSequence = "ABABAA";
        tennisApplication.playGameForInput(playsSequence);
    }
}
