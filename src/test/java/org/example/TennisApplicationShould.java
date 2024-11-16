package org.example;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.EnabledIfEnvironmentVariable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TennisApplicationTest {

    private TennisApplication tennisApplication;
    private ByteArrayOutputStream baos;

    @BeforeEach
    void setUp() {
        tennisApplication = new TennisApplication();
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    @Nested
    class AcceptanceTests {
        @Test
        @EnabledIfEnvironmentVariable(named = "OUTSIDE", matches = ".*")
        void should_play_and_print_an_entire_game_without_deuces_and_advantages() {
            // WHEN
            tennisApplication.playGameForInput("ABABAA");

            // THEN
            String expectedOutput = """
                Player A : 15 / Player B : 0
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Player A wins the game
                """;
            assertThat(baos.toString()).isEqualTo(expectedOutput);
        }

        @Test()
        @EnabledIfEnvironmentVariable(named = "OUTSIDE", matches = ".*")
        void should_play_and_print_an_entire_game_with_deuces_and_advantages() {
            // WHEN
            tennisApplication.playGameForInput("ABABAA");

            // THEN
            String expectedOutput = """
                Player A : 15 / Player B : 0
                Player A : 15 / Player B : 15
                Player A : 30 / Player B : 15
                Player A : 30 / Player B : 30
                Player A : 40 / Player B : 30
                Deuce
                advantage Player A
                Deuce
                advantage Player B
                Deuce
                advantage Player A
                Player A wins the game
                """;
            assertThat(baos.toString()).isEqualTo(expectedOutput);
        }
    }

    @Nested
    public class ErrorCases{
        @ParameterizedTest()
        @ValueSource(
                strings = {
                        "",
                        " ",
                        "\t \n"
                }
        )
        void should_throw_an_error_on_blank_input(String input) {
            // WHEN
            ThrowableAssert.ThrowingCallable throwingCallable = () -> tennisApplication.playGameForInput(input);

            // THEN
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("input shouldn't be blank");
        }

        @ParameterizedTest
        @ValueSource(
                strings = {
                        "ABC",
                        "AB AB",
                        "AB AB"
                }
        )
        void should_throw_an_error_on_invalid_character(String input) {
            // WHEN
            ThrowableAssert.ThrowingCallable throwingCallable = () -> tennisApplication.playGameForInput(input);

            // THEN
            assertThatThrownBy(throwingCallable)
                    .isInstanceOf(IllegalArgumentException.class)
                    .hasMessage("invalid character in input: %s".formatted(input));
        }
    }

    @Test
    void should_print_correctly_sequence_A() {
        // WHEN
        tennisApplication.playGameForInput("A");

        // THEN
        String expectedOutput = """
                Player A : 15 / Player B : 0
                """;
        assertThat(baos.toString()).isEqualTo(expectedOutput);
    }
}
