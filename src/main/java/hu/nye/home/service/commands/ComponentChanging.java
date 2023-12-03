package hu.nye.home.service.commands;

import hu.nye.home.model.Hero;
import hu.nye.home.model.MapVO;
import hu.nye.home.service.util.Utils;
import hu.nye.home.service.util.UtilsImp;
import hu.nye.home.service.validator.Validators;
import hu.nye.home.service.validator.ValidatorsImp;

import java.util.List;
import java.util.SortedMap;

public class ComponentChanging {
    public static String changeComponent(String coordinate1, String compoment1, MapVO mapVO, SortedMap<String, List<String>> components, Hero hero) {
        Utils utils = new UtilsImp();
        Validators validators = new ValidatorsImp();
        String answer = null;
        int[] position = utils.string2Int(coordinate1);
        String[][] map = mapVO.getMap();
        boolean checkCoordinate = validators.IsTheCoordinateCorrect(position, mapVO.getMapSize());
        if(checkCoordinate){
            if(compoment1.equals("H")){
                hero.setStartCoordinate(position);
            }
            components.get(coordinate1).add(compoment1);
            map[position[1] - 1][position[0] - 1] = compoment1;
            mapVO.setMap(map);
            answer = "A módosítás megtörtént.";
            return answer;
        }else {
            answer = "A módosítás nem történt meg. A koordináta a határfalnál vagy nincs pályán.";
            return answer;
        }
    }
}
