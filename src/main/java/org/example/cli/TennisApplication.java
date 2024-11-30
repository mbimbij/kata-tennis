package org.example.cli;

import org.example.core.Score;
import org.example.core.ScoreFactory;
import org.example.core.usecases.CountScoreForSequence;
import org.example.core.usecases.InputParser;
import org.example.core.usecases.PlayerToScore;

import java.util.List;


public class TennisApplication {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide a tennis game input as a single argument, in the format [AB]+");
            System.exit(1);
        }
        run(args[0]);
    }

    public static void run(String inputString) {
        ConsolePrinter printer = new ConsolePrinter();
        ConsoleScoreFormatter scoreFormatter = new ConsoleScoreFormatter();

        List<PlayerToScore> scoreSequence = InputParser.parse(inputString);
        Score initialScore = ScoreFactory.getInstance().loveAll();

        CountScoreForSequence countScore = new CountScoreForSequence(initialScore);

        countScore.executeSequence(scoreSequence,
                score -> printer.printScoreAsNeeded(score, scoreFormatter));
    }
}
