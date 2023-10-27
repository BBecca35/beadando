package hu.nye.home.ui;

import hu.nye.home.service.map.BufferedReaderMapReader;
import hu.nye.home.service.map.MapReader;
import hu.nye.home.service.map.exceptions.MapReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException, MapReadException {
        InputStream inputStream = hu.nye.home.Main.class.getClassLoader().getResource("wumpuszinput.txt").openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        MapReader mapReader = new BufferedReaderMapReader(bufferedReader);
        List<String> stringList = mapReader.readMap();
        //System.out.println(stringList);

        MapConverterImp mapConverterImp = new MapConverterImp();
        String[][] oldMap = mapConverterImp.convertMap(stringList);
        //String name = inputFromConsole();
        System.out.println();
        String[][] newMap = mapConverterImp.convertMap(stringList);

        Scanner scanner = new Scanner(System.in);
        System.out.println("Felhasználónév: ");
        scanner.nextLine();
        System.out.println("Pályaszerkesztés kezdése: ");
        System.out.println("-------------------------------");

        boolean kapcsolo = true;
        while(kapcsolo){
            System.out.println("Szeretne szerkeszteni a pályán? (y/n)");
            String answerYesOrNo = scanner.nextLine();
            if(answerYesOrNo.equals("n")){
                kapcsolo = false;
            }
            else {
                System.out.println("Hozzáadni szeretne vagy visszavonni! (hozzáad/visszavon)");
                String answer = scanner.nextLine();
                if (answer.equals("hozzáad")) {
                    System.out.println("Adja meg, milyen elemet szeretne hozzáadni! (W, H, U, P, G)");
                    String compoment1 = scanner.nextLine();
                    System.out.println("Hova kívánja hozzáadni (pl: A1)?");
                    String coordinate1 = scanner.nextLine();
                    newMap = Commands.addComponent(coordinate1, compoment1, oldMap);
                    mapConverterImp.printMap(newMap);
                } else if (answer.equals("visszavon")) {
                    System.out.println("Honnan szeretne visszavonni (pl: A1)?");
                    String coordinate2 = scanner.nextLine();
                    String[][] anotherMap;
                    anotherMap = Commands.removeComponent(coordinate2, oldMap, newMap);
                    mapConverterImp.printMap(anotherMap);
                }
            }
        }
        System.out.println("Program leáll.");
        System.out.println();

    }
}
