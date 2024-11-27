package org.example.domain;

import org.example.infra.Printer;

import java.util.List;

public class CountScore {

    private final Printer printer;

    public CountScore(Printer printer) {
        this.printer = printer;
    }

    public void playGame(List<PlayerToScore> scoreSequence) {
        Score gameScore = ScoreFactory.loveAll();
        for (PlayerToScore cmd : scoreSequence) {
            gameScore = switch (cmd) {
                case A -> gameScore.scorePointForPlayerA();
                case B -> gameScore.scorePointForPlayerB();
            };
            printer.print(ScoreFormatter.format(gameScore));
        }
    }
}
