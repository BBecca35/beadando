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

class EditorMenuTest {

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
        simulatedInput = new ByteArrayInputStream("1\nH\nA1\n1\n2\n3\n2\n2\n".getBytes());
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
    public void testEdit() {
        MapVO originalMap = new MapVO(5, 5);
        VariableMap modifiedMap = new VariableMap(5, 5);

        EditorMenu.edit(originalMap, modifiedMap);

        // Ellenőrizzük a szimulált kimenetet
        String expectedOutput = "-------------------------------\n" +
                "Kérem adja meg az opció számát!\n" +
                "1. hozzáadás\n" +
                "2. visszavonás\n" +
                "3. A szerkesztő bezárása\n" +
                "-------------------------------\n" +
                "A hozzáadás megkezdése.\n" +
                "-------------------------------\n" +
                "Kérem adja meg az opció számát!\n" +
                "1. hozzáadás\n" +
                "2. visszavonás\n" +
                "3. A szerkesztő bezárása\n" +
                "-------------------------------\n" +
                "A visszavonás sikertelen. Még nem történt hozzáadás.\n" +
                "--------------------------------------------\n" +
                "Szeretné tovább szerkeszteni a pályát? \n" +
                "1. igen\n" +
                "2. nem\n" +
                "--------------------------------------------\n" +
                "A pályaszerkesztő bezárása.\n";

        assertEquals(expectedOutput, simulatedOutput.toString());

        // Ellenőrizzük a módosított térképet (helyettesítse megfelelő szabályokkal)
        String[][] map = modifiedMap.getMap();
        // Ezen a ponton ellenőrizheti, hogy a térkép helyesen módosult-e

        // Példa ellenőrzéshez:
        assertEquals("H", map[0][0]);
    }

}