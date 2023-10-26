package hu.nye.home.model;

public class RawMap {
    private String map;
    private String fixed;

    public RawMap(String map, String fixed) {
        this.map = map;
        this.fixed = fixed;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getFixed() {
        return fixed;
    }

    public void setFixed(String fixed) {
        this.fixed = fixed;
    }

    @Override
    public String toString() {
        return "map\n" + map;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RawMap rawMap = (RawMap) o;

        if (!map.equals(rawMap.map)) return false;
        return fixed.equals(rawMap.fixed);
    }

    @Override
    public int hashCode() {
        int result = map.hashCode();
        result = 31 * result + fixed.hashCode();
        return result;
    }


}
