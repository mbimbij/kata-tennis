package org.example.cli;

import org.example.shared.core.Score;

public class ConsolePrinter {
    public void print(String formattedScore) {
        System.out.println(formattedScore);
    }

    public void printScoreAsNeeded(Score gameScore, ConsoleScoreFormatter scoreFormatter) {
        print(scoreFormatter.format(gameScore));
    }
}
