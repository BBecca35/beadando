package hu.nye.home.service.commands;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringConverterTest {
    @Test
    public void testString2Int() {
        // Teszt esetek
        String[] inputStrings = {
                "A1",  // Az első teszteset
                "C10", // A második teszteset
                "B25"  // A harmadik teszteset
        };

        int[][] expectedResults = {
                {1, 1},  // Az első teszteset elvárt eredménye
                {3, 10}, // A második teszteset elvárt eredménye
                {2, 25}  // A harmadik teszteset elvárt eredménye
        };

        for (int i = 0; i < inputStrings.length; i++) {
            int[] result = StringConverter.string2Int(inputStrings[i]);
            assertArrayEquals(expectedResults[i], result);
        }
    }
}