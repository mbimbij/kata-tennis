package org.example.countscoreforsequence;

import org.example.shared.ScoreFactory;

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
        CountScoreForSequence countScore = new CountScoreForSequence(printer, scoreFormatter, scoreFactory.loveAll());
        countScore.executeSequence(scoreSequence);
    }
}
