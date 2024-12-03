package org.example.shared.infra;

import org.example.shared.core.GameScore;

public class ConsolePrinter {
    public void print(String formattedScore) {
        System.out.println(formattedScore);
    }

    public void printScoreAsNeeded(GameScore gameScore, ConsoleScoreFormatter scoreFormatter) {
        print(scoreFormatter.format(gameScore));
    }
}
