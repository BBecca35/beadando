package hu.nye.home.service.commands;

import hu.nye.home.model.MapVO;
import hu.nye.home.model.VariableMap;

public class ComponentRemoving {
    public static void removeComponent(String coordinate2, MapVO old, VariableMap changed) {
        int[] position = StringConverter.string2Int(coordinate2);
        String[][] map = changed.getMap();

        for (int i = 0; i < map.length; i++) {
            for(int j = 0; j < map[0].length; j++){
                if(j == position[0]-1 && i == position[1]-1){
                    map[i][j] = old.getMap()[i][j];
                }
            }
        }
        changed.setMap(map);

    }
}
