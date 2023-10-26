package hu.nye.home.ui;

import hu.nye.home.Main;
import hu.nye.home.service.map.BufferedReaderMapReader;
import hu.nye.home.service.map.MapReader;
import hu.nye.home.service.map.exceptions.MapReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

public class input {
    public static void main(String[] args) throws IOException, MapReadException {
        InputStream inputStream = Main.class.getClassLoader().getResource("wumpuszinput.txt").openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        List<String> stringList = mapReader.readMap();
        //System.out.println(stringList);
        MapConverterImp mapPrinterImp = new MapConverterImp();
        mapPrinterImp.printMap(mapPrinterImp.convertMap(stringList));
    }

}
