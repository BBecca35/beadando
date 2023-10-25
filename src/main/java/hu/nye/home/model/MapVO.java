package hu.nye.home.model;

import java.util.Arrays;
import java.util.Objects;

public class MapVO {
    private final int numberOfRows;
    private final int numberOFColums;
    private final String[][] map;
    private final boolean[][] fixed;

    public MapVO(int numbersOfRows, int numberOFColums, String[][] map, boolean[][] fixed) {
        this.numberOfRows = numbersOfRows;
        this.numberOFColums = numberOFColums;
        this.map = map;
        this.fixed = fixed;
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

    public boolean[][] getFixed() {
        return fixed;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("MapVO{");
        sb.append("numberOfRows=").append(numberOfRows);
        sb.append(", numberOFColums=").append(numberOFColums);
        sb.append(", map=").append(Arrays.toString(map));
        sb.append(", fixed=").append(Arrays.toString(fixed));
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
        if (!Arrays.deepEquals(map, mapVO.map)) return false;
        return Arrays.deepEquals(fixed, mapVO.fixed);
    }

    @Override
    public int hashCode() {
        int result = numberOfRows;
        result = 31 * result + numberOFColums;
        result = 31 * result + Arrays.deepHashCode(map);
        result = 31 * result + Arrays.deepHashCode(fixed);
        return result;
    }

    private int[][] deepCopy(int[][] array) {
        int[][] result = null;

        if (array != null) {
            result = new int[array.length][];
            for (int i = 0; i < array.length; i++) {
                result[i] = Arrays.copyOf(array[i], array[i].length);
            }
        }

        return result;
    }
}


