package hu.nye.home.service.commands;

import hu.nye.home.model.Hero;
import hu.nye.home.model.MapVO;

import java.util.*;

public class ArrowShooting {

    public static String shootArrow(MapVO mapVO, Hero hero){
        String messega = null;
        SortedMap<String, int[]> components = new TreeMap<>();
        String[][] map = mapVO.getMap();
        int heroColumn = hero.getActualCoordinate()[0];
        int heroRow = hero.getActualCoordinate()[1];
        int[] coordinate = new int[2];
        String directionOfShooting = hero.getHeading();
        int arrow = hero.getNumberOfArrows();
        for (int i = 0; i < arrow; i++) {
            switch (directionOfShooting) {
                case "Eszak":
                    heroRow--;
                    break;
                case "Del":
                    heroRow++;
                    break;
                case "Nyugat":
                    heroColumn--;
                    break;
                case "Kelet":
                    heroColumn++;
                    break;
            }
            if (isValidIndex(map, heroRow, heroColumn)) {
                String target = map[heroRow][heroColumn];
                coordinate[0] = heroRow;
                coordinate[1] = heroColumn;
                components.put(target, coordinate);
            }
        }
        boolean shoot = false;
        for (Map.Entry<String, int[]> entry : components.entrySet()) {
            System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue().toString());
            if(entry.getKey().equals("U")){
                map[entry.getValue()[0]][entry.getValue()[1]] = "_";
                messega = "A nyílvessző eltalált egy wumpuszt.";
                arrow -= 1;
                shoot = true;
                break;
            } else if (entry.getKey().equals("W")) {
                messega = "A nyílvessző eltalált egy belső falat.";
                arrow -= 1;
                shoot = true;
                break;
            }
        }
        if(!shoot){
            messega = "A nyílvessző eltalált a pálya egyik határfalát.";
            arrow -= 1;
        }

        hero.setNumberOfArrows(arrow);
        mapVO.setMap(map);
        return messega;
    }

    public static boolean isValidIndex(String[][] array, int i, int j) {
        return i >= 1 && i < array.length && j >= 1 && j < array[0].length;
    }
}
