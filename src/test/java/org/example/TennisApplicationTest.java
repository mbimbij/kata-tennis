package org.example;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InOrder;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class TennisApplicationTest {

    private TennisApplication tennisApplication;
    private TennisGame tennisGame;
    private ByteArrayOutputStream baos;

    @BeforeEach
    void setUp() {
        tennisGame = spy(new TennisGame());
        tennisApplication = new TennisApplication(tennisGame);
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

    @Test
    void should_play_and_print_an_entire_game_without_deuces_and_advantages_and_playerA_wins() {
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

    @Test
    void should_play_and_print_an_entire_game_without_deuces_and_advantages_and_playerB_wins() {
        // WHEN
        tennisApplication.playGameForInput("ABABBB");

        // THEN
        String expectedOutput = """
                    Player A : 15 / Player B : 0
                    Player A : 15 / Player B : 15
                    Player A : 30 / Player B : 15
                    Player A : 30 / Player B : 30
                    Player A : 30 / Player B : 40
                    Player B wins the game
                    """;
        assertThat(baos.toString()).isEqualTo(expectedOutput);
    }

    @Test()
    void should_play_and_print_an_entire_game_with_deuces_and_advantages_and_playerA_wins() {
        // WHEN
        tennisApplication.playGameForInput("ABABABA");

        // THEN
        String expectedOutput = """
                    Player A : 15 / Player B : 0
                    Player A : 15 / Player B : 15
                    Player A : 30 / Player B : 15
                    Player A : 30 / Player B : 30
                    Player A : 40 / Player B : 30
                    Deuce
                    Advantage Player A
                    """;
        assertThat(baos.toString()).isEqualTo(expectedOutput);
    }

    @Test()
    void should_print_correctly_a_game_until_advantage_playerB() {
        // WHEN
        tennisApplication.playGameForInput("ABABABB");

        // THEN
        String expectedOutput = """
                    Player A : 15 / Player B : 0
                    Player A : 15 / Player B : 15
                    Player A : 30 / Player B : 15
                    Player A : 30 / Player B : 30
                    Player A : 40 / Player B : 30
                    Deuce
                    Advantage Player B
                    """;
        assertThat(baos.toString()).isEqualTo(expectedOutput);
    }


    @Test
    void should_call_game_scoring_method_multiple_times_for_input_sequence() {
        // GIVEN
        InOrder inOrder = inOrder(tennisGame);

        // WHEN
        tennisApplication.playGameForInput("AB");

        // THEN
        inOrder.verify(tennisGame).scorePointForPlayerA();
        inOrder.verify(tennisGame).scorePointForPlayerB();
    }
}