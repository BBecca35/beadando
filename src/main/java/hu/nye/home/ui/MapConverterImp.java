package hu.nye.home.ui;

import java.util.List;

public class MapConverterImp implements MapConverter {

    @Override
    public String[][] convertMap(List<String> inputlist) {
        int numRows = inputlist.size() - 1;
        int numCols = inputlist.get(1).length();
        String[][] resultArray = new String[numRows][numCols];

        for (int i = 1; i <= numRows; i++) {
            String row = inputlist.get(i);
            for (int j = 0; j < numCols; j++) {
                resultArray[i - 1][j] = String.valueOf(row.charAt(j));
            }
        }

        return resultArray;
    }

    @Override
    public void printMap(String[][] input) {
        for (int i = 0; i < input.length; i++) {
            if (i == 0) {
                System.out.print("  ");
                for (int j = 0; j < input[0].length; j++) {
                    System.out.print((char) ('A' + j) + " ");
                }
                System.out.println();
            }
            for (int j = 0; j < input[0].length; j++) {
                if (j == 0) {
                    System.out.print(i + 1 + " ");
                }
                System.out.print(input[i][j] + " ");
            }
            System.out.println();
        }
    }


}
