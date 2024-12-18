package org.example.shared.core.usecases.scoresequence;

import org.assertj.core.api.ThrowableAssert;
import org.example.shared.core.usecases.InputParser;
import org.example.shared.core.usecases.PlayerToScore;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.shared.core.usecases.PlayerToScore.A;
import static org.example.shared.core.usecases.PlayerToScore.B;

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
        List<PlayerToScore> commands = InputParser.parse("ABBAABABBB");

        // THEN
        List<PlayerToScore> expected = List.of(A, B, B, A, A, B, A, B, B, B);
        assertThat(commands).isEqualTo(expected);
    }
}
