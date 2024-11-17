package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;

import static org.example.GameScore.loveAll;

public class TennisApplication {
    private final GameScore initialScore;

    public TennisApplication(GameScore initialScore) {
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

        List<PlayerToScoreFor> commandList = Arrays.stream(input.split(""))
                .map(PlayerToScoreFor::valueOf).toList();

        applyCommandList2(commandList);
    }

    private void applyCommandList2(List<PlayerToScoreFor> commandList) {
        GameScore gameScore = initialScore;
        for (PlayerToScoreFor cmd : commandList) {
            gameScore = switch (cmd) {
                case A -> gameScore.scorePointForPlayerA();
                case B -> gameScore.scorePointForPlayerB();
            };
            ScorePrinter.print(gameScore);
        }
    }

    public enum PlayerToScoreFor {
        A, B
    }
}
