package org.example;

import org.apache.commons.lang3.StringUtils;

public class TennisApplication {
    public void playGameForInput(String input) {
        if(StringUtils.isBlank(input)){
            throw new IllegalArgumentException("input shouldn't be blank");
        }
        if(!input.matches("[AB]+")){
            throw new IllegalArgumentException("invalid character in input: %s".formatted(input));
        }
        System.out.println("Player A : 15 / Player B : 0");
    }
}
