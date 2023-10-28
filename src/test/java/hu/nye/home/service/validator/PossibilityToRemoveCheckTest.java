package hu.nye.home.service.validator;

import hu.nye.home.model.MapVO;
import hu.nye.home.model.VariableMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PossibilityToRemoveCheckTest {

    private MapVO mapVO;
    private VariableMap variableMap;

    @BeforeEach
    public void setUp() {
        // Inicializáljuk a változókat minden teszteset előtt
        mapVO = new MapVO(5, 5); // Az adott méretre inicializáljuk
        variableMap = new VariableMap(5, 5); // Az adott méretre inicializáljuk
    }

    @Test
    public void testApplicableRemoval() {
        // Első teszteset: Nincs módosítás a térképen, nem lehetséges a törlés
        boolean isApplicable1 = PossibilityToRemoveCheck.applicableRemoval(mapVO, variableMap);
        assertFalse(isApplicable1);

        // Második teszteset: Van módosítás a térképen, lehetséges a törlés
        String[][] originalMap = mapVO.getMap();
        String[][] changedMap = variableMap.getMap();
        changedMap[1][1] = "G"; // Módosítás az egyik mezőn
        variableMap.setMap(changedMap);

        boolean isApplicable2 = PossibilityToRemoveCheck.applicableRemoval(mapVO, variableMap);
        assertTrue(isApplicable2);
    }
}