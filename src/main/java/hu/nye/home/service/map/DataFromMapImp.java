package hu.nye.home.service.map;

import java.util.List;

public class DataFromMapImp implements DataFromMap {

    @Override
    public String[][] convertMap(List<String> inputlist1) {
        int numRows = inputlist1.size() - 1;
        int numCols = inputlist1.get(1).length();
        String[][] resultArray = new String[numRows][numCols];

        for (int i = 1; i <= numRows; i++) {
            String row = inputlist1.get(i);
            for (int j = 0; j < numCols; j++) {
                resultArray[i - 1][j] = String.valueOf(row.charAt(j));
            }
        }

        return resultArray;
    }

    @Override
    public String[] splitMap(List<String> inputlist2) {
        String firstLine = inputlist2.get(0);
        String[] tokens = firstLine.split(" ");
        return tokens;
    }

    @Override
    public int wumpuszCounter(List<String> inputlist2) {
        String[][] map = convertMap(inputlist2);
        int numberOfWumpusz = 0;
        for(int i = 0; i < map.length; i++){
            for (int j = 0; j < map[0].length; j++) {
                if(map[i][j].equals("W")){
                    numberOfWumpusz += 1;
                }
            }
        }
        return numberOfWumpusz;
    }
}
