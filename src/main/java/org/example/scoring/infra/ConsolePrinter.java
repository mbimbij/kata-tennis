package org.example.scoring.infra;


import org.example.scoring.core.GameScore;

public class ConsolePrinter {
    public void print(String formattedScore) {
        System.out.println(formattedScore);
    }

    public void printScoreAsNeeded(GameScore gameScore, ConsoleScoreFormatter scoreFormatter) {
        print(scoreFormatter.format(gameScore));
    }
}
