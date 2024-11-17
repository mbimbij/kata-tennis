package org.example;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.example.PlayerScore.THIRTY;
import static org.example.PlayerScore.WIN;
import static org.junit.jupiter.api.Assertions.*;

class ScorePrinterTest {
    @Test
    void print_correctly_when_playerB_won() {
        // GIVEN
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        System.setOut(new PrintStream(baos));

        // WHEN
        ScorePrinter.print(new GameScore(THIRTY, WIN));

        // THEN
        assertEquals("Player B wins the game\n", baos.toString());
    }
}
