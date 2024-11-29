package org.example.core.usecases;

import lombok.RequiredArgsConstructor;
import org.example.cli.ConsoleScoreFormatter;
import org.example.cli.ConsolePrinter;
import org.example.core.Score;

import java.util.List;

@RequiredArgsConstructor
public class CountScoreForSequence {

    private final ConsolePrinter printer;
    private final ConsoleScoreFormatter scoreFormatter;
    private final Score initialScore;

    public void executeSequence(List<PlayerToScore> scoreSequence) {
        Score gameScore = initialScore;
        for (PlayerToScore cmd : scoreSequence) {
            gameScore = switch (cmd) {
                case A -> gameScore.scorePointForPlayerA();
                case B -> gameScore.scorePointForPlayerB();
            };
            printer.print(scoreFormatter.format(gameScore));
        }
    }
}
