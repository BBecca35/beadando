package hu.nye.home.model;

import java.util.Arrays;

public class MapVO {
    private int mapSize;
    private String[][] map;

    public MapVO(int mapSize, String[][] map) {
        this.mapSize = mapSize;
        this.map = map;
    }

    public MapVO(String[][] map) {
        this.map = map;
    }

    public MapVO() {

    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
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

        MapVO that = (MapVO) o;

        if (mapSize != that.mapSize) return false;
        return Arrays.deepEquals(map, that.map);
    }

    @Override
    public int hashCode() {
        int result = mapSize;
        result = 31 * result + Arrays.deepHashCode(map);
        return result;
    }

    @Override
    public String toString() {
        StringBuilder mapString = new StringBuilder();
        for (int i = 0; i < map.length; i++) {
            if (i == 0) {
                mapString.append("   ");
                for (int j = 0; j < map[0].length; j++) {
                    mapString.append((char) ('A' + j)).append(" ");
                }
                mapString.append("\n");

            }
            if(i < 9){
                mapString.append(" ");
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
