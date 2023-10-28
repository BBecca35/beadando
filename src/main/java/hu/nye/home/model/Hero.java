package hu.nye.home.model;

import java.util.StringJoiner;

public class Hero {
    private String heading;
    private boolean havingGold;
    private int numberOfArrows;

    public Hero(String heading, boolean havingGold, int numberOfArrows) {
        this.heading = heading;
        this.havingGold = havingGold;
        this.numberOfArrows = numberOfArrows;
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

    @Override
    public String toString() {
        return new StringJoiner(", ", Hero.class.getSimpleName() + "[", "]")
                .add("heading='" + heading + "'")
                .add("havingGold=" + havingGold)
                .add("numberOfArrows=" + numberOfArrows)
                .toString();
    }
}
