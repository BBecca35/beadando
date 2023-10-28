package hu.nye.home.model;

import hu.nye.home.service.map.DataFromMapImp;

import java.util.Arrays;

public class VariableMap {
    private int numberOfColumns;
    private int numberOfRows;
    private String[][] map;

    public VariableMap(int numberOfColumns, int numberOfRows, String[][] map) {
        this.numberOfColumns = numberOfColumns;
        this.numberOfRows = numberOfRows;
        this.map = map;
    }

    public VariableMap(String[][] map) {
        this.map = map;
    }

    public VariableMap(int i, int i1) {
    }

    public int getNumberOfColumns() {
        return numberOfColumns;
    }

    public void setNumberOfColumns(int numberOfColumns) {
        this.numberOfColumns = numberOfColumns;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public void setNumberOfRows(int numberOfRows) {
        this.numberOfRows = numberOfRows;
    }

    public String[][] getMap() {
        return map;
    }

    public void setMap(String[][] map) {
        this.map = map;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        VariableMap that = (VariableMap) o;

        if (numberOfColumns != that.numberOfColumns) return false;
        if (numberOfRows != that.numberOfRows) return false;
        return Arrays.deepEquals(map, that.map);
    }

    @Override
    public int hashCode() {
        int result = numberOfColumns;
        result = 31 * result + numberOfRows;
        result = 31 * result + Arrays.deepHashCode(map);
        return result;
    }

    DataFromMapImp mapConverterImp = new DataFromMapImp();

    @Override
    public String toString() {
        StringBuilder mapString = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            if (i == 0) {
                mapString.append("  ");
                for (int j = 0; j < map[0].length; j++) {
                    mapString.append((char) ('A' + j)).append(" ");
                }
                mapString.append("\n");
            }
            for (int j = 0; j < map[0].length; j++) {
                if (j == 0) {
                    mapString.append(i + 1).append(" ");
                }
                mapString.append(map[i][j]).append(" ");
            }
            mapString.append("\n");
        }

        return mapString.toString();
    }
}
