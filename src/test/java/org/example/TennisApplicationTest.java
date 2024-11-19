package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class TennisApplicationTest {

    @Test()
    void should_print_correctly_an_entire_game_with_playerA_winning() {
        // GIVEN stdout redirection for further assertions
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        // WHEN
        TennisApplication.playGameForInput("ABABABABBAAA");

        // THEN
        String expectedOutput = """
                    Player A : 15 / Player B : 0
                    Player A : 15 / Player B : 15
                    Player A : 30 / Player B : 15
                    Player A : 30 / Player B : 30
                    Player A : 40 / Player B : 30
                    Deuce
                    Advantage Player A
                    Deuce
                    Advantage Player B
                    Deuce
                    Advantage Player A
                    Player A wins the game
                    """;
        assertThat(baos.toString()).isEqualTo(expectedOutput);
    }
}
