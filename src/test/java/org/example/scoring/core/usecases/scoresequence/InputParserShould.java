package org.example.scoring.core.usecases.scoresequence;

import org.assertj.core.api.ThrowableAssert;
import org.example.scoring.core.Player;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.example.scoring.core.usecases.InputParser;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.scoring.core.Player.A;
import static org.example.scoring.core.Player.B;

class InputParserShould {
    @ParameterizedTest()
    @ValueSource(
            strings = {
                    "",
                    " ",
                    "\t \n"
            }
    )
    void throw_an_error_on_blank_input(String input) {
        // WHEN
        ThrowableAssert.ThrowingCallable throwingCallable = () -> InputParser.parse(input);

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
    void throw_an_error_on_invalid_character(String input) {
        // WHEN
        ThrowableAssert.ThrowingCallable throwingCallable = () -> InputParser.parse(input);

        // THEN
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("invalid character in input: %s".formatted(input));
    }

    @Test
    void parse_proper_input() {
        // WHEN
        List<Player> commands = InputParser.parse("ABBAABABBB");

        // THEN
        List<Player> expected = List.of(A, B, B, A, A, B, A, B, B, B);
        assertThat(commands).isEqualTo(expected);
    }
}
