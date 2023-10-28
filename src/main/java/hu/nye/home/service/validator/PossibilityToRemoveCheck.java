package hu.nye.home.service.validator;

import hu.nye.home.model.MapVO;
import hu.nye.home.model.VariableMap;

public class PossibilityToRemoveCheck {
    public static boolean applicableRemoval(MapVO mapVO, VariableMap variableMap){
        String[][] originalMap = mapVO.getMap();
        String[][] changedMap = variableMap.getMap();
        boolean switcher = false;
        for(int i = 0; i < mapVO.getMap().length; i++){
            for (int j = 0; j < mapVO.getMap()[0].length; j++) {
                if(!(originalMap[i][j].equals(changedMap[i][j]))){
                    switcher = true;
                    break;
                }
            }
        }
        return switcher;
    }

}
