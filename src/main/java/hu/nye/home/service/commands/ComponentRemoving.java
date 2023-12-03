package hu.nye.home.service.commands;

import hu.nye.home.model.MapVO;
import hu.nye.home.service.util.Utils;
import hu.nye.home.service.util.UtilsImp;
import hu.nye.home.service.validator.Validators;
import hu.nye.home.service.validator.ValidatorsImp;

import java.util.List;
import java.util.SortedMap;

public class ComponentRemoving {
    public static String removeComponent(String coordinate2, SortedMap<String, List<String>> components, MapVO changed) {
        Validators validators = new ValidatorsImp();
        String answer = null;
        Utils utils = new UtilsImp();
        int[] position = utils.string2Int(coordinate2);
        String[][] map = changed.getMap();
        int mapsize = map.length;
        boolean checkCoordinate;
        checkCoordinate = validators.IsTheCoordinateCorrect(position, mapsize);
        if (!checkCoordinate) {
            answer = "A visszavonás nem történt meg. A koordináta a határfalnál vagy nincs pályán.";
        }else {
            if(components.get(coordinate2).size() == 1){
                answer = "A visszavonás nem történt meg. Az adott koordináta alaphelyzetben van.";
            }
            else {
                int IndexOfLastComponent = components.get(coordinate2).size()-1;
                components.get(coordinate2).remove(IndexOfLastComponent);
                int IndexOfTheOldComponent = components.get(coordinate2).size()-1;
                String oldComponent = components.get(coordinate2).get(IndexOfTheOldComponent);
                map[position[1] - 1][position[0] - 1] = oldComponent;
                changed.setMap(map);
                answer = "A visszavonás megtörtént.";
            }
        }
        return answer;
    }
}
