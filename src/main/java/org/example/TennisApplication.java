package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import static org.example.GameScore.loveAll;

public class TennisApplication {
    private final GameScore initialScore;

    private TennisApplication(GameScore initialScore) {
        this.initialScore = initialScore;
    }

    static TennisApplication newStandardTennisApplication() {
        return new TennisApplication(loveAll());
    }

    public void playGameForInput(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input shouldn't be blank");
        }
        if (!input.matches("[AB]+")) {
            throw new IllegalArgumentException("invalid character in input: %s".formatted(input));
        }

        List<Command> commandList = Arrays.stream(input.split(""))
                .map(Command::valueOf).toList();

        applyCommandList(commandList);
    }

    private void applyCommandList(List<Command> commandList) {
        GameScore gameScore = initialScore;
        for (Command cmd : commandList) {
            gameScore = switch (cmd) {
                case A -> gameScore.scorePointForPlayerA();
                case B -> gameScore.scorePointForPlayerB();
            };
            ScorePrinter.print(gameScore.format());
        }
    }

    public enum Command {
        A, B
    }
}
