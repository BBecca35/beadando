package hu.nye.home.service.map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataFromMapImpTest {

    private DataFromMap dataFromMap;

    @BeforeEach
    public void setUp() {
        // Inicializáljuk a DataFromMapImp osztály példányát minden teszteset előtt
        dataFromMap = new DataFromMapImp();
    }

    @Test
    public void testConvertMap() {
        List<String> inputList1 = new ArrayList<>();
        inputList1.add("3");
        inputList1.add("WWW");
        inputList1.add("PWP");

        String[][] resultArray = dataFromMap.convertMap(inputList1);

        assertEquals(3, resultArray.length);
        assertEquals(3, resultArray[0].length);
        assertEquals("W", resultArray[0][0]);
        assertEquals("P", resultArray[1][0]);
        assertEquals("P", resultArray[2][1]);
    }

    @Test
    public void testSplitMap() {
        List<String> inputList2 = new ArrayList<>();
        inputList2.add("5 4");

        String[] tokens = dataFromMap.splitMap(inputList2);

        assertEquals(2, tokens.length);
        assertEquals("5", tokens[0]);
        assertEquals("4", tokens[1]);
    }

    @Test
    public void testWumpuszCounter() {
        List<String> inputList3 = new ArrayList<>();
        inputList3.add("4");
        inputList3.add("WWW");
        inputList3.add("PWP");
        inputList3.add("WWP");
        inputList3.add("WPP");

        int numberOfWumpusz = dataFromMap.wumpuszCounter(inputList3);

        assertEquals(6, numberOfWumpusz);
    }
}