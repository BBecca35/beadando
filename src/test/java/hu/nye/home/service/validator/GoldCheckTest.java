package hu.nye.home.service.validator;

import hu.nye.home.model.VariableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GoldCheckTest {

    private VariableMap variableMap;

    @BeforeEach
    public void setUp() {
        // Inicializáljuk a változókat minden teszteset előtt
        variableMap = new VariableMap(5, 5); // Az adott méretre inicializáljuk

        // Feltöltjük a térképet egy kezdeti állapottal
        String[][] map = variableMap.getMap();
        map[1][1] = "G"; // Például, az első pozícióban van arany
        variableMap.setMap(map);
    }

    @Test
    public void testApplicableGold() {
        // Létrehozunk egy példányt a GoldCheck osztályból
        boolean isApplicable;

        // Első teszteset: Az arany alkalmazható, mert a térképen van
        isApplicable = GoldCheck.applicableGold(variableMap, "G");
        assertTrue(isApplicable);

        // Második teszteset: Az arany nem alkalmazható, mert nincs a térképen
        isApplicable = GoldCheck.applicableGold(variableMap, "H"); // "H" nem található a térképen
        assertFalse(isApplicable);

        // Harmadik teszteset: Az arany nem alkalmazható, mert más komponenst keresünk
        isApplicable = GoldCheck.applicableGold(variableMap, "P"); // "P" sem található a térképen
        assertFalse(isApplicable);
    }
}