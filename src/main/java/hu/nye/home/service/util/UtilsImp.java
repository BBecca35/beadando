package hu.nye.home.service.util;

import hu.nye.home.model.GameStateModel;
import hu.nye.home.model.MapVO;

public class UtilsImp implements Utils{
    public UtilsImp() {
    }

    @Override
    public String convertNumberToDirection(int number) {
        String component = null;
        if(number == 1){
            component = "Eszak";
        } else if (number == 2) {
            component = "Del";
        } else if (number == 3) {
            component = "Kelet";
        }else if (number == 4) {
            component = "Nyugat";
        }
        return component;
    }

    @Override
    public String[][] createMap(int n) {
        String[][] palya = new String[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 || i == n - 1 || j == 0 || j == n - 1) {
                    palya[i][j] = "W";
                } else {
                    palya[i][j] = "_";
                }
            }
        }
        return palya;
    }

    @Override
    public String convertMapVoMapToString(MapVO mapVO) {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < mapVO.getMap().length; i++) {
            for (int j = 0; j < mapVO.getMap()[0].length; j++) {
                String components = mapVO.getMap()[i][j];
                builder.append(components);
            }
        }

        return builder.toString();
    }

    @Override
    public String[][] convertStringToArray(GameStateModel model) {
        String[][] map = new String[model.getMapSize()][model.getMapSize()];
        int counter = 0;
        for (int i = 0; i < model.getMapSize(); i++) {
            for (int j = 0; j < model.getMapSize(); j++) {
                char component = model.getMap().charAt(counter);
                map[i][j] = Character.toString(component);
                counter++;
            }
        }
        return map;
    }

    @Override
    public int[] string2Int(String positionInString) {
        int[] coordination = new int[2];
        try {
            char firstChar = positionInString.charAt(0);
            String numberString = positionInString.substring(1);
            int xCoordinate = firstChar - 'A' + 1;
            int yCoordinate = Integer.parseInt(numberString);
            coordination = new int[]{xCoordinate, yCoordinate};
        }catch (Exception e){
            coordination = new int[]{0, 0};
        }
        return coordination;
    }

    @Override
    public String convertNumberToComponent(int number) {
        String component = null;
        if(number == 1){
            component = "W";
        } else if (number == 2) {
            component = "H";
        } else if (number == 3) {
            component = "U";
        }else if (number == 4){
            component = "P";
        } else if (number == 5) {
            component = "G";
        }
        return component;
    }

    @Override
    public int countWumpus(int mapSize) {
        int numberOfWumpuses = 0;
        if(mapSize < 9){
            numberOfWumpuses = 1;
        }else if(mapSize < 15){
            numberOfWumpuses = 2;
        } else if (mapSize < 21) {
            numberOfWumpuses = 3;
        }
        return numberOfWumpuses;
    }
}
