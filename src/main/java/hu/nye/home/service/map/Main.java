package hu.nye.home.service.map;

import hu.nye.home.service.map.exceptions.MapReadException;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class Main {
    public static void main(String[] args) throws MapReadException {
        InputStream inputStream = hu.nye.home.Main.class.getClassLoader().getResource("wumpuszinput.txt").openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        List<String> stringList = mapReader.readMap();
        System.out.println(stringList);
    }
}
