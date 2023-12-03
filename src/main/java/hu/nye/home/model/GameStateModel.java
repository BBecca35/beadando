package hu.nye.home.model;

public class GameStateModel {
    private int id;
    private String mapName;
    private int mapSize;
    private String map;
    private int playerId;
    public GameStateModel() {
    }

    public GameStateModel(String mapName, int mapSize, String map, int playerId) {
        this.mapName = mapName;
        this.mapSize = mapSize;
        this.map = map;
        this.playerId = playerId;
    }

    public GameStateModel(int id, String mapName, int mapSize ,String map, int playerId) {
        this.id = id;
        this.mapName = mapName;
        this.mapSize = mapSize;
        this.map = map;
        this.playerId = playerId;
    }

    public GameStateModel(String wH__u__p__g) {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMapName() {
        return mapName;
    }

    public void setMapName(String mapName) {
        this.mapName = mapName;
    }

    public int getMapSize() {
        return mapSize;
    }

    public void setMapSize(int mapSize) {
        this.mapSize = mapSize;
    }

    public String getMap() {
        return map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("GameStateModel{");
        sb.append("id=").append(id);
        sb.append(", mapName='").append(mapName).append('\'');
        sb.append(", mapSize=").append(mapSize);
        sb.append(", map='").append(map).append('\'');
        sb.append(", playerId=").append(playerId);
        sb.append('}');
        return sb.toString();
    }
}
