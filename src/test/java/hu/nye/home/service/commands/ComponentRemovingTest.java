package hu.nye.home.service.commands;

import hu.nye.home.model.MapVO;
import hu.nye.home.model.VariableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ComponentRemovingTest {
    private VariableMap variableMap;
    private MapVO oldMap;

    @BeforeEach
    public void setUp() {
        // Inicializáljuk a változókat minden teszteset előtt
        variableMap = new VariableMap(5, 5); // Az adott méretre inicializáljuk
        oldMap = new MapVO(5,5); // Az adott méretre inicializáljuk
        // Feltöltjük a térképeket egy kezdeti állapottal
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                variableMap.getMap()[i][j] = "G"; // Például, minden mezőn "G" van
                oldMap.getMap()[i][j] = "O"; // Például, minden mezőn "O" van
            }
        }
    }

    @Test
    public void testRemoveComponent() {
        // Létrehozunk egy példányt a ComponentRemoving osztályból
        ComponentRemoving componentRemover = new ComponentRemoving();

        // Első teszteset: Komponens eltávolítása sikeres
        String coordinate1 = "2,3";
        ComponentRemoving.removeComponent(coordinate1, oldMap, variableMap);
        String[][] map = variableMap.getMap();
        assertEquals("O", map[2][1]); // Az adott pozíción az "O" értéket várjuk

        // Második teszteset: Érvénytelen pozíció eltávolítása
        String coordinate2 = "6,5"; // Érvénytelen pozíció
        ComponentRemoving.removeComponent(coordinate2, oldMap, variableMap);
        // Ellenőrizzük, hogy a térkép nem változott
        assertArrayEquals(oldMap.getMap(), variableMap.getMap());

        // Harmadik teszteset: Pozíció már üres, nem történik változás
        String coordinate3 = "1,1"; // Üres pozíció
        ComponentRemoving.removeComponent(coordinate3, oldMap, variableMap);
        // Ellenőrizzük, hogy a térkép nem változott
        assertArrayEquals(oldMap.getMap(), variableMap.getMap());
    }
}