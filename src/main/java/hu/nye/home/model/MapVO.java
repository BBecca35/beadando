package hu.nye.home.model;

import java.util.Arrays;

public class MapVO {
    private final int numberOfRows;
    private final int numberOFColums;
    private final String[][] map;

    public MapVO(int numbersOfRows, int numberOFColums, String[][] map) {
        this.numberOfRows = numbersOfRows;
        this.numberOFColums = numberOFColums;
        this.map = map;
    }

    public int getNumberOfRows() {
        return numberOfRows;
    }

    public int getNumberOFColums() {
        return numberOFColums;
    }

    public String[][] getMap() {
        return map;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MapVO{");
        sb.append("numberOfRows=").append(numberOfRows);
        sb.append(", numberOFColums=").append(numberOFColums);
        sb.append(", map=").append(Arrays.toString(map));
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MapVO mapVO = (MapVO) o;

        if (numberOfRows != mapVO.numberOfRows) return false;
        if (numberOFColums != mapVO.numberOFColums) return false;
        return Arrays.deepEquals(map, mapVO.map);
    }

    @Override
    public int hashCode() {
        int result = numberOfRows;
        result = 31 * result + numberOFColums;
        result = 31 * result + Arrays.deepHashCode(map);
        return result;
    }
}


