package hu.nye.home.ui;

import hu.nye.home.model.MapVO;
import hu.nye.home.model.VariableMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class RemoverMenuTest {
    private InputStream originalSystemIn;
    private PrintStream originalSystemOut;
    private ByteArrayOutputStream simulatedOutput;
    private ByteArrayInputStream simulatedInput;

    @BeforeEach
    public void setUp() {
        // Mentjük az eredeti System.in és System.out állapotát
        originalSystemIn = System.in;
        originalSystemOut = System.out;

        // Szimulált bemenet és kimenet létrehozása
        simulatedInput = new ByteArrayInputStream("A1\n".getBytes());
        simulatedOutput = new ByteArrayOutputStream();

        System.setIn(simulatedInput);
        System.setOut(new PrintStream(simulatedOutput));
    }

    @AfterEach
    public void tearDown() {
        // Visszaállítjuk az eredeti System.in és System.out állapotát
        System.setIn(originalSystemIn);
        System.setOut(originalSystemOut);
    }

    @Test
    public void testRemover() {
        MapVO mapVO = new MapVO(5, 5);
        VariableMap variableMap = new VariableMap(5, 5);

        RemoverMenu.remover(mapVO, variableMap);

        // Ellenőrizzük a szimulált kimenetet
        String expectedOutput = "A visszavonás megkezdése.\n" +
                "Honnan szeretne visszavonni? (pl: A1)\n" +
                "[[0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0]]\n";

        assertEquals(expectedOutput, simulatedOutput.toString());
    }
}