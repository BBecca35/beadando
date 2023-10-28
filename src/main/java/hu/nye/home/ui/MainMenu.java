package hu.nye.home.ui;

import hu.nye.home.model.MapVO;
import hu.nye.home.model.VariableMap;
import hu.nye.home.service.map.DataFromMapImp;

import java.util.List;
import java.util.Scanner;

import static hu.nye.home.ui.EditorMenu.edit;

public class MainMenu {
    public static void runTheProgram(List<String> stringList){
        DataFromMapImp mapConverterImp = new DataFromMapImp();
        String[][] oldMap = mapConverterImp.convertMap(stringList);
        String[][] newMap = mapConverterImp.convertMap(stringList);
        MapVO originalMap = new MapVO(oldMap[0].length, oldMap[0].length, oldMap);
        VariableMap changedMap = new VariableMap(newMap[0].length, newMap[0].length, newMap);

        Scanner scanner = new Scanner(System.in);

        boolean kapcsolo1 = true;
        while (kapcsolo1){
            System.out.println("Felhasználónév: ");
            String username = scanner.nextLine();
            if(username == null){
                System.out.println("-------------------------------");
                System.out.println("A Felhasználónevet nem lehet üresen hagyni.");
                System.out.println("Kérem töltse ki!");
                System.out.println("-------------------------------");
            }else {
                kapcsolo1 = false;
            }
        }

        boolean kapcsolo2 = true;
        while(kapcsolo2){
            System.out.println("Menü:");
            System.out.println("-------------------------------");
            System.out.println("Kérem írja be az opció számát!");
            System.out.println("1. Játék (Hamarosan)");
            System.out.println("2. Pályaszerkesztés");
            System.out.println("3. Pontszámok (Hamarosan)");
            System.out.println("4. Bezárás");
            System.out.println("-------------------------------");
            int option = scanner.nextInt();
            if(option == 1){
                System.out.println("Az alábbi számmal ellátott funkció,");
                System.out.println("még nincs lefejlesztve.");
            } else if (option == 2) {
                System.out.println("Pályaszerkesztés kezdése: ");
                System.out.println("-------------------------------");
                System.out.println(changedMap.toString());
                System.out.println("-------------------------------");
                //mapConverterImp.printMap(originalMap.getMap());
                edit(originalMap, changedMap);
            } else if(option == 3){
                System.out.println("Az alábbi számmal ellátott funkció,");
                System.out.println("még nincs lefejlesztve.");
            }else if(option == 4){
                kapcsolo2 = false;
            } else{
                System.out.println("Ilyen számmal opció nem létezik.");
            }

        }
        System.out.println("A Program leáll.");

    }
}
