package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;


public class TennisApplication {
    private final Score initialScore;
    private final ScoreFormatter scoreFormatter;
    public TennisApplication(Score initialScore, ScoreFormatter scoreFormatter) {
        this.initialScore = initialScore;
        this.scoreFormatter = scoreFormatter;
    }

    public void playGameForInput(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input shouldn't be blank");
        }
        if (!input.matches("[AB]+")) {
            throw new IllegalArgumentException("invalid character in input: %s".formatted(input));
        }

        List<Command> commandList = parse(input);
        apply(commandList);
    }

    private static List<Command> parse(String input) {
        return Arrays.stream(input.split(""))
                .map(Command::valueOf).toList();
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

    public enum Command {
        A, B
    }
}
