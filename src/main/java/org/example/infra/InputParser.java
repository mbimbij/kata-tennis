package org.example.infra;

import org.apache.commons.lang3.StringUtils;
import org.example.domain.PlayerToScore;

import java.util.Arrays;
import java.util.List;

public class InputParser {
    public static List<PlayerToScore> parse(String input) {
        if (StringUtils.isBlank(input)) {
            throw new IllegalArgumentException("input shouldn't be blank");
        }
        if (!input.matches("[AB]+")) {
            throw new IllegalArgumentException("invalid character in input: %s".formatted(input));
        }

        return Arrays.stream(input.split(""))
                .map(PlayerToScore::valueOf).toList();
    }
}
