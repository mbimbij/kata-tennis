package org.example.acceptance;

import org.example.cli.TennisApplication;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;

class TennisApplicationShould {

    @Test()
    void play_an_entire_game_and_print_output_correctly() {
        // GIVEN stdout redirection for further assertions
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        // WHEN
        TennisApplication.run("ABABABABBAAA");

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
