package org.example.cli;

import org.example.shared.core.Score;
import org.example.shared.core.ScoreFactory;
import org.example.shared.core.usecases.CountScoreForSequence;
import org.example.shared.core.usecases.InputParser;
import org.example.shared.core.usecases.PlayerToScore;
import org.example.shared.infra.ConsolePrinter;
import org.example.shared.infra.ConsoleScoreFormatter;

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
