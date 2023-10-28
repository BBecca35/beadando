package hu.nye.home.service.validator;

import hu.nye.home.model.VariableMap;

public class GoldCheck {
    public static boolean applicableGold(VariableMap variableMap, String Gold){
        String[][] map = variableMap.getMap();
        int numberOfGold = 0;
        for (String[] strings : map) {
            for (int j = 0; j < map[0].length; j++) {
                if (strings[j].equals(Gold)) {
                    numberOfGold = 1;
                    break;
                }
            }
        }
        return numberOfGold != 1;
    }
}
