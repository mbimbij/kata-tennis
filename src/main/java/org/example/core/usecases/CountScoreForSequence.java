package org.example.core.usecases;

import lombok.RequiredArgsConstructor;
import org.example.core.Score;

import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class CountScoreForSequence {

    private final Score initialScore;

    public Score executeSequence(List<PlayerToScore> scoreSequence, Consumer<Score> scorePrinter) {
        Score gameScore = initialScore;
        for (PlayerToScore cmd : scoreSequence) {
            gameScore = switch (cmd) {
                case A -> gameScore.scorePointForPlayerA();
                case B -> gameScore.scorePointForPlayerB();
            };
            scorePrinter.accept(gameScore);
        }
        return gameScore;
    }

}
