package org.example;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ExtendWith(MockitoExtension.class)
class TennisApplicationTest {

    private TennisApplication tennisApplication;
    private ByteArrayOutputStream baos;

    @BeforeEach
    void setUp() {
        GameScoreFactory gameScoreFactory = new IntegerBasedGameScoreFactory();
        tennisApplication = new TennisApplication(gameScoreFactory.loveAll());
        baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));
    }

    @Nested
    public class ErrorCases {
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

    @Test()
    void should_print_correctly_an_entire_game_with_playerA_winning() {
        // WHEN
        tennisApplication.playGameForInput("ABABABABBAAA");

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
