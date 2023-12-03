package hu.nye.home.model;

import java.util.Arrays;

public class Hero {

    private int id;
    private String heading;
    private boolean havingGold;
    private int numberOfArrows;
    private int[] startCoordinate;
    private int[] actualCoordinate;
    private int gameStateID;

    public Hero(String heading, boolean havingGold, int numberOfArrows, int[] startCoordinate, int[] actualCoordinate, int gameStateID) {
        this.heading = heading;
        this.havingGold = havingGold;
        this.numberOfArrows = numberOfArrows;
        this.startCoordinate = startCoordinate;
        this.actualCoordinate = actualCoordinate;
        this.gameStateID = gameStateID;
    }

    public Hero(String heading, int[] actualCoordinate, int gameStateID) {
        this.heading = heading;
        this.actualCoordinate = actualCoordinate;
        this.gameStateID = gameStateID;
    }

    public Hero(boolean havingGold) {
        this.havingGold = havingGold;

    }

    public Hero() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public boolean isHavingGold() {
        return havingGold;
    }

    public void setHavingGold(boolean havingGold) {
        this.havingGold = havingGold;
    }

    public int getNumberOfArrows() {
        return numberOfArrows;
    }

    public void setNumberOfArrows(int numberOfArrows) {
        this.numberOfArrows = numberOfArrows;
    }

    public int[] getStartCoordinate() {
        return startCoordinate;
    }

    public void setStartCoordinate(int[] startCoordinate) {
        this.startCoordinate = startCoordinate;
    }

    public int[] getActualCoordinate() {
        return actualCoordinate;
    }

    public void setActualCoordinate(int[] actualCoordinate) {
        this.actualCoordinate = actualCoordinate;
    }

    public int getGameStateID() {
        return gameStateID;
    }

    public void setGameStateID(int gameStateID) {
        this.gameStateID = gameStateID;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Hero{");
        sb.append("id=").append(id);
        sb.append(", heading='").append(heading).append('\'');
        sb.append(", havingGold=").append(havingGold);
        sb.append(", numberOfArrows=").append(numberOfArrows);
        sb.append(", startCoordinate=").append(Arrays.toString(startCoordinate));
        sb.append(", actualCoordinate=").append(Arrays.toString(actualCoordinate));
        sb.append(", gameStateID=").append(gameStateID);
        sb.append('}');
        return sb.toString();
    }
}
