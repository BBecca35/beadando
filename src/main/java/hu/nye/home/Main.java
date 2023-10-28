package hu.nye.home;


import hu.nye.home.model.Hero;
import hu.nye.home.service.map.BufferedReaderMapReader;
import hu.nye.home.service.map.MapReader;
import hu.nye.home.service.exceptions.MapReadException;
import hu.nye.home.service.map.DataFromMapImp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import static hu.nye.home.ui.MainMenu.runTheProgram;

public class Main {
    public static void main(String[] args) throws MapReadException, IOException {
        InputStream inputStream = hu.nye.home.Main.class.getClassLoader().getResource("wumpuszinput.txt").openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        List<String> stringList = mapReader.readMap();
        DataFromMapImp dataFromMapImp = new DataFromMapImp();
        String[] firstRow = dataFromMapImp.splitMap(stringList);
        int arrows = dataFromMapImp.wumpuszCounter(stringList);
        Hero BBecca = new Hero(firstRow[3], false, arrows);
        runTheProgram(stringList);
    }
}