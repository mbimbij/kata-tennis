package org.example.shared.core.usecases;

import lombok.RequiredArgsConstructor;
import org.example.shared.core.GameScore;
import org.example.shared.core.Player;

import java.util.List;
import java.util.function.Consumer;

@RequiredArgsConstructor
public class ComputeSingleGameScoreForSequence {

    private final GameScore initialScore;

    public GameScore executeSequence(List<Player> scoreSequence, Consumer<GameScore> scorePrinter) {
        GameScore gameScore = initialScore;
        for (Player cmd : scoreSequence) {
            gameScore = switch (cmd) {
                case A -> gameScore.scorePointForPlayerA();
                case B -> gameScore.scorePointForPlayerB();
            };
            scorePrinter.accept(gameScore);
        }
        return gameScore;
    }

    public GameScore executeSequence(List<Player> scoreSequence) {
        return executeSequence(scoreSequence, score -> {});
    }

}
