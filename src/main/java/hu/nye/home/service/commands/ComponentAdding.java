package hu.nye.home.service.commands;

import hu.nye.home.model.VariableMap;
import hu.nye.home.service.validator.GoldCheck;
import hu.nye.home.service.validator.HeroCheck;
import hu.nye.home.service.validator.OtherComponentCheck;

public class ComponentAdding {
    public static boolean addComponent(int[] coordinate1, String compoment1, VariableMap variableMap) {
        String[][] map = variableMap.getMap();
        boolean appGold = GoldCheck.applicableGold(variableMap, compoment1);
        boolean appHero = HeroCheck.applicableHero(variableMap, compoment1, coordinate1);
        boolean appComp = OtherComponentCheck.applicableComponent(coordinate1);
        if(appComp){
            return false;
        }
        else if(compoment1.equals("G") && !appGold){
            return false;
        }
        else if(compoment1.equals("H") && !appHero){
            return false;
        }
        else {
            for (int i = 1; i < map.length; i++) {
                for (int j = 1; j < map[0].length; j++) {
                    if ((j == coordinate1[0] - 1 && i == coordinate1[1] - 1)) {
                        map[i][j] = compoment1;
                    }
                }
            }
            variableMap.setMap(map);
        }
        return true;
    }
}
