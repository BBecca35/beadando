package hu.nye.home.service.util;

import hu.nye.home.model.GameStateModel;
import hu.nye.home.model.MapVO;

public interface Utils {
    String convertNumberToDirection(int number);
    String[][] createMap(int n);
    String convertMapVoMapToString(MapVO mapVO);
    String[][] convertStringToArray(GameStateModel model);
    int[] string2Int(String positionInString);
    String convertNumberToComponent(int number);
    int countWumpus(int mapSize);

}
