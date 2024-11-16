package org.example;

import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;

public class TennisApplication {
    private final TennisGame tennisGame;

    public TennisApplication(TennisGame tennisGame) {
        this.tennisGame = tennisGame;
    }

    public void playGameForInput(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input shouldn't be blank");
        }
        if (!input.matches("[AB]+")) {
            throw new IllegalArgumentException("invalid character in input: %s".formatted(input));
        }

        Arrays.stream(input.split(""))
                .map(PlayerToScoreFor::valueOf)
                .map(gameCommand -> switch (gameCommand) {
                    case A -> tennisGame.scorePointForPlayerA();
                    case B -> tennisGame.scorePointForPlayerB();
                })
                .forEach(ScorePrinter::print);
    }

    public enum PlayerToScoreFor {
        A, B
    }
}
