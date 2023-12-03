package hu.nye.home.service.commands;

import hu.nye.home.model.MapVO;
import hu.nye.home.service.util.Utils;
import hu.nye.home.service.util.UtilsImp;

import java.util.List;
import java.util.SortedMap;

public class ComponentDeleting {
    public static String deleteComponent(SortedMap<String, List<String>> components, MapVO mapVO, String coordinate){
        Utils utils = new UtilsImp();
        int[] position = utils.string2Int(coordinate);
        String answer = null;
        String[][] map = mapVO.getMap();
        if(components.containsKey(coordinate)){
            map[position[1]-1][position[0]-1] = "_";
            components.remove(coordinate);
            answer = "A törlés megtörtént";
        }else{
            answer = "A törlés nem történt meg. Az adott koordinátán nem történt még hozzáadás.";
        }
        return answer;
    }
}
