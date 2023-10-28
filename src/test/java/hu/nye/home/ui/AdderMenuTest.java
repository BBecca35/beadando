package hu.nye.home.ui;

import hu.nye.home.model.VariableMap;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.*;

class AdderMenuTest {

    private InputStream originalSystemIn;
    private ByteArrayInputStream simulatedInput;

    @BeforeEach
    public void setUp() {
        // Mentjük az eredeti System.in állapotát
        originalSystemIn = System.in;

        // Szimulált bemenet létrehozása ("H\nA1\n")
        simulatedInput = new ByteArrayInputStream("H\nA1\n".getBytes());
        System.setIn(simulatedInput);
    }

    @AfterEach
    public void tearDown() {
        // Visszaállítjuk az eredeti System.in-t
        System.setIn(originalSystemIn);
    }

    @Test
    public void testAdder() {
        // Létrehozunk egy példányt a VariableMap osztályból
        VariableMap variableMap = new VariableMap(5, 5); // Az adott méretre inicializáljuk

        // Teszteljük a AdderMenu.adder függvényt
        AdderMenu.adder(variableMap);

        // Ellenőrizzük, hogy a térkép helyesen módosult-e
        String[][] map = variableMap.getMap();
        assertEquals("H", map[0][0]);
    }
}