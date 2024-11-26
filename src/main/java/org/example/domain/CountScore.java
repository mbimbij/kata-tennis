package org.example.domain;

import lombok.RequiredArgsConstructor;
import org.example.infra.Printer;

import java.util.List;

@RequiredArgsConstructor
public class CountScore {

    private final Printer printer;
    private final ScoreFormatter scoreFormatter;
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
