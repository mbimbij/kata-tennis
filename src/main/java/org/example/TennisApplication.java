package org.example;

import java.util.List;


public class TennisApplication {
    private final Score initialScore;
    private final ScoreFormatter scoreFormatter;
    public TennisApplication(Score initialScore, ScoreFormatter scoreFormatter) {
        this.initialScore = initialScore;
        this.scoreFormatter = scoreFormatter;
    }

    public void playGameForInput(String input) {
        List<Command> commandList = InputParser.parse(input);
        apply(commandList);
    }

    private void apply(List<Command> commandList) {
        Score gameScore = initialScore;
        for (Command cmd : commandList) {
            gameScore = switch (cmd) {
                case A -> gameScore.scorePointForPlayerA();
                case B -> gameScore.scorePointForPlayerB();
            };
            Printer.print(scoreFormatter.format(gameScore));
        }
    }

}
