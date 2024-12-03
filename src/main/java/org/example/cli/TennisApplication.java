package org.example.cli;

import org.example.scoring.core.GameScore;
import org.example.scoring.core.GameScoreFactory;
import org.example.scoring.core.usecases.ComputeSingleGameScoreForSequence;
import org.example.scoring.core.usecases.InputParser;
import org.example.scoring.core.Player;
import org.example.scoring.infra.ConsolePrinter;
import org.example.scoring.infra.ConsoleScoreFormatter;

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

        List<Player> scoreSequence = InputParser.parse(inputString);
        GameScore initialScore = GameScoreFactory.getInstance().loveAll();

        ComputeSingleGameScoreForSequence countScore = new ComputeSingleGameScoreForSequence(initialScore);

        countScore.executeSequence(scoreSequence,
                score -> printer.printScoreAsNeeded(score, scoreFormatter));
    }
}
