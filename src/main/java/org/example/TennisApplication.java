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
        if(input.equals("A")) {
            System.out.println("Player A : 15 / Player B : 0");
        }else {
            System.out.println("Player A : 0 / Player B : 15");
        }
    }
}
