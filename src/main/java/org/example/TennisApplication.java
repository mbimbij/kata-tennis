package org.example;

import org.apache.commons.lang3.StringUtils;

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
        if (input.equals("A")) {
            tennisGame.scorePointForPlayerA();
        } else if (input.equals("B")) {
            System.out.print("""
                    Player A : 0 / Player B : 15
                    """);
        } else {
            System.out.print("""
                    Player A : 15 / Player B : 0
                    Player A : 15 / Player B : 15
                    """);
        }
    }

}
