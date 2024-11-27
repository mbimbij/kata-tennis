package org.example;

import org.example.domain.PlayerToScore;
import org.example.domain.CountScore;
import org.example.domain.ScoreFactory;
import org.example.domain.ScoreFormatter;
import org.example.infra.InputParser;
import org.example.infra.Printer;

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
        List<PlayerToScore> scoreSequence = InputParser.parse(inputString);
        Printer printer = new Printer();
        ScoreFactory scoreFactory = new ScoreFactory();
        ScoreFormatter scoreFormatter = scoreFactory.createScoreFormatter();
        new CountScore(printer, scoreFormatter, scoreFactory.loveAll()).playGame(scoreSequence);
    }
}
