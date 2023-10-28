package hu.nye.home.service.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class OtherComponentCheckTest {

    @Test
    public void testApplicableComponent() {
        // Első teszteset: Más komponens alkalmazható, mert a koordináta érvényes
        int[] coordinate1 = {3, 3};
        boolean isApplicable1 = OtherComponentCheck.applicableComponent(coordinate1);
        assertFalse(isApplicable1);

        // Második teszteset: Más komponens alkalmazható, mert az egyik koordináta az érvényen kívül van
        int[] coordinate2 = {1, 3};
        boolean isApplicable2 = OtherComponentCheck.applicableComponent(coordinate2);
        assertTrue(isApplicable2);

        // Harmadik teszteset: Más komponens alkalmazható, mert a másik koordináta az érvényen kívül van
        int[] coordinate3 = {3, 6};
        boolean isApplicable3 = OtherComponentCheck.applicableComponent(coordinate3);
        assertTrue(isApplicable3);

        // Negyedik teszteset: Más komponens alkalmazható, mert mindkét koordináta az érvényen kívül van
        int[] coordinate4 = {0, 7};
        boolean isApplicable4 = OtherComponentCheck.applicableComponent(coordinate4);
        assertTrue(isApplicable4);
    }
}