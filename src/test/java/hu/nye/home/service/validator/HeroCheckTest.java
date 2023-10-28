package hu.nye.home.service.validator;

import hu.nye.home.model.VariableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HeroCheckTest {

    private VariableMap variableMap;

    @BeforeEach
    public void setUp() {
        // Inicializáljuk a változókat minden teszteset előtt
        variableMap = new VariableMap(5, 5); // Az adott méretre inicializáljuk

        // Feltöltjük a térképet egy kezdeti állapottal
        String[][] map = variableMap.getMap();
        map[1][1] = "H"; // Például, az első pozícióban van hős
        variableMap.setMap(map);
    }

    @Test
    public void testApplicableHero() {
        // Létrehozunk egy példányt a HeroCheck osztályból
        boolean isApplicable;

        // Első teszteset: A hős alkalmazható, mert a térképen van
        int[] coordinate = {2, 2};
        isApplicable = HeroCheck.applicableHero(variableMap, "H", coordinate);
        assertTrue(isApplicable);

        // Második teszteset: A hős nem alkalmazható, mert egy másik hőst próbálunk hozzáadni
        coordinate = new int[] {3, 3};
        isApplicable = HeroCheck.applicableHero(variableMap, "H", coordinate);
        assertFalse(isApplicable);

        // Harmadik teszteset: A hős nem alkalmazható, mert túl sok hős van a térképen
        int numRows = variableMap.getNumberOfRows();
        String heroType = "H";
        String[][] map = variableMap.getMap();
        for (int i = 1; i <= numRows; i++) {
            map[i - 1][i - 1] = heroType; // Túl sok hős hozzáadása a térképhez
        }
        variableMap.setMap(map);
        coordinate = new int[] {1, 1};
        isApplicable = HeroCheck.applicableHero(variableMap, heroType, coordinate);
        assertFalse(isApplicable);
    }
}