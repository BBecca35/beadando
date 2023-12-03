package hu.nye.home.service.validator;

import hu.nye.home.model.MapVO;

public interface Validators {
    boolean IsTheCoordinateCorrect(int[] coordinate, int mapsize);
    boolean applicableHeroOrGold(MapVO mapVO, String HeroOrGold);
    boolean applicableWumpus(MapVO mapVO, String Wumpusz);

}
