package org.example;

import java.util.List;


public class TennisApplication {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.err.println("Please provide a tennis game input as a single argument, in the format [AB]+");
            System.exit(1);
        }
        playGameForInput(args[0]);
    }

    public static void playGameForInput(String input) {
        List<Command> commandList = InputParser.parse(input);
        apply(commandList);
    }

    private static void apply(List<Command> commandList) {
        Score gameScore = Scores.loveAll();
        for (Command cmd : commandList) {
            gameScore = switch (cmd) {
                case A -> gameScore.scorePointForPlayerA();
                case B -> gameScore.scorePointForPlayerB();
            };
            Printer.print(ScoreFormatter.format(gameScore));
        }
    }

}
