package hu.nye.home.service.validator;

import hu.nye.home.model.MapVO;

public class ValidatorsImp implements Validators{

    public ValidatorsImp() {
    }

    @Override
    public boolean IsTheCoordinateCorrect(int[] coordinate, int mapsize) {
        if(coordinate[0] <= 0 || coordinate[0] >= mapsize){
            return false;
        }else if(coordinate[1] <= 0 || coordinate[1] >= mapsize) {
            return false;
        }
        else {
            return true;
        }
    }

    @Override
    public boolean applicableHeroOrGold(MapVO mapVO, String HeroOrGold) {
        String[][] map = mapVO.getMap();
        int numberOfHerosOrGolds = 0;
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j].equals(HeroOrGold)){
                    numberOfHerosOrGolds += 1;
                }
            }
        }
        if(numberOfHerosOrGolds == 0){
            return false;
        } else if (numberOfHerosOrGolds > 1) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean applicableWumpus(MapVO mapVO, String Wumpusz) {
        boolean switcher = true;
        String[][] map = mapVO.getMap();
        int size = mapVO.getMapSize();
        int numberOfWumpusesOnTheMap = 0;
        for(int i = 0; i < size; i++){
            for(int j = 0; j < size; j++){
                if(map[i][j].equals(Wumpusz)){
                    numberOfWumpusesOnTheMap +=1;
                }
            }
        }if (numberOfWumpusesOnTheMap == 0){
            switcher = false;
        } else if (size < 9 && numberOfWumpusesOnTheMap > 1) {
            switcher = false;
        } else if (size < 15 && numberOfWumpusesOnTheMap > 2) {
            switcher = false;
        } else if (size < 21 && numberOfWumpusesOnTheMap > 3) {
            switcher = false;
        }
        return switcher;
    }
}
