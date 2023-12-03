package hu.nye.home.model;

public class Player {
    private int id;
    private String name;
    private boolean deathMeter;
    private boolean winMeter;
    private int gameState;

    public Player(int id, String name, boolean deathMeter, boolean winMeter, int gameState) {
        this.id = id;
        this.name = name;
        this.deathMeter = deathMeter;
        this.winMeter = winMeter;
        this.gameState = gameState;
    }

    public Player(String name, boolean deathMeter, boolean winMeter, int gameState) {
        this.name = name;
        this.deathMeter = deathMeter;
        this.winMeter = winMeter;
        this.gameState = gameState;
    }



    public Player() {
    }

    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public boolean isDeathMeter() {
        return deathMeter;
    }
    public void setDeathMeter(boolean deathMeter) {
        this.deathMeter = deathMeter;
    }
    public boolean isWinMeter() {
        return winMeter;
    }
    public void setWinMeter(boolean winMeter) {
        this.winMeter = winMeter;
    }
    public int getGameState() {
        return gameState;
    }
    public void setGameState(int gameState) {
        this.gameState = gameState;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Player{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", deathMeter=").append(deathMeter);
        sb.append(", WinMeter=").append(winMeter);
        sb.append(", gameState=").append(gameState);
        sb.append('}');
        return sb.toString();
    }
}
