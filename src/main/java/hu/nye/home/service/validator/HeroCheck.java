package hu.nye.home.service.validator;

import hu.nye.home.model.VariableMap;
import hu.nye.home.service.commands.StringConverter;

public class HeroCheck {
    public static boolean applicableHero(VariableMap variableMap, String Hero, int[] coordinate) {
        boolean switcher = true;
        String[][] map = variableMap.getMap();
        int numberOfHeros = 0;
        for (String[] strings : map) {
            for (int j = 0; j < map[0].length; j++) {
                if (strings[j].equals(Hero)) {
                    numberOfHeros += 1;
                }
            }
        }
        if (map[coordinate[0] - 1][coordinate[1] - 1].equals("P")) {
            switcher = false;
        } else if (variableMap.getNumberOfRows() < 9 && numberOfHeros == 1) {
            switcher = false;
        } else if (variableMap.getNumberOfRows() < 15 && numberOfHeros == 2) {
            switcher = false;
        } else if (variableMap.getNumberOfRows() > 14 && numberOfHeros == 3) {
            switcher = false;
        }
        return switcher;
    }
}
