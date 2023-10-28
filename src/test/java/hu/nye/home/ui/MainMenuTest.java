package hu.nye.home.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MainMenuTest {

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
        simulatedInput = new ByteArrayInputStream("teszt\n2\n4\n".getBytes());
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
    public void testRunTheProgram() {
        List<String> stringList = new ArrayList<>();
        stringList.add("5 5");
        stringList.add("UUUUU");
        stringList.add("UHHHU");
        stringList.add("UHHHU");
        stringList.add("UHHHU");
        stringList.add("UUUUU");

        MainMenu.runTheProgram(stringList);

        // Ellenőrizzük a szimulált kimenetet
        String expectedOutput = "Felhasználónév: \n" +
                "-------------------------------\n" +
                "A Felhasználónevet nem lehet üresen hagyni.\n" +
                "Kérem töltse ki!\n" +
                "-------------------------------\n" +
                "Menü:\n" +
                "-------------------------------\n" +
                "Kérem írja be az opció számát!\n" +
                "1. Játék (Hamarosan)\n" +
                "2. Pályaszerkesztés\n" +
                "3. Pontszámok (Hamarosan)\n" +
                "4. Bezárás\n" +
                "-------------------------------\n" +
                "Pályaszerkesztés kezdése: \n" +
                "-------------------------------\n" +
                "[[0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0], [0, 0, 0, 0, 0]]\n" +
                "-------------------------------\n" +
                "A Program leáll.\n";

        assertEquals(expectedOutput, simulatedOutput.toString());
    }
}