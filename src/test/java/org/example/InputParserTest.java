package org.example;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.example.Command.A;
import static org.example.Command.B;

class InputParserTest {
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
    void should_throw_an_error_on_invalid_character(String input) {
        // WHEN
        ThrowableAssert.ThrowingCallable throwingCallable = () -> InputParser.parse(input);

        // THEN
        assertThatThrownBy(throwingCallable)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("invalid character in input: %s".formatted(input));
    }

    @Test
    void should_parse_proper_input() {
        // WHEN
        List<Command> commands = InputParser.parse("ABBAABABBB");

        // THEN
        List<Command> expected = List.of(A, B, B, A, A, B, A, B, B, B);
        assertThat(commands).isEqualTo(expected);
    }
}
