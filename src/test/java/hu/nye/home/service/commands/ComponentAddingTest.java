package hu.nye.home.service.commands;

import hu.nye.home.model.VariableMap;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComponentAddingTest {

    @Test
    public void testAddComponent() {
        ComponentAdding componentAdder = new ComponentAdding();

        // Létrehozunk egy példányt a VariableMap osztályból
        VariableMap variableMap = new VariableMap(5, 5); // Az adott méretre inicializáljuk

        // Első teszteset: Hozzáadás sikeres
        int[] coordinate1 = {2, 3};
        String component1 = "G";
        assertTrue(ComponentAdding.addComponent(coordinate1, component1, variableMap));

        // Ellenőrizzük, hogy a térképen a komponens valóban hozzáadódott
        String[][] map = variableMap.getMap();
        assertEquals(component1, map[2][1]);

        // Második teszteset: Hozzáadás sikertelen, mert már létező komponens
        int[] coordinate2 = {2, 3};
        String component2 = "H"; // H betűt már tartalmazza a térkép
        assertFalse(ComponentAdding.addComponent(coordinate2, component2, variableMap));

        // Harmadik teszteset: Hozzáadás sikertelen, mert az adott pozíció már foglalt
        int[] coordinate3 = {1, 1};
        String component3 = "G";
        assertFalse(ComponentAdding.addComponent(coordinate3, component3, variableMap));

        // Negyedik teszteset: Hozzáadás sikertelen, mert nem megfelelő pozíció
        int[] coordinate4 = {6, 5}; // Érvénytelen pozíció
        String component4 = "G";
        assertFalse(ComponentAdding.addComponent(coordinate4, component4, variableMap));
    }
}