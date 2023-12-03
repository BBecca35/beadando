package hu.nye.home.service.commands;

import hu.nye.home.model.Hero;
import hu.nye.home.model.MapVO;
import hu.nye.home.model.Player;
import hu.nye.home.service.validator.Validators;
import hu.nye.home.service.validator.ValidatorsImp;
import hu.nye.home.ui.game.Game;

public class HeroMoving {
    public HeroMoving() {
    }
    public String moveHero(Hero hero, MapVO mapVO, Player player, Game game) {
        Validators validators = new ValidatorsImp();
        String messega = null;
        int[] coordinateOfMoving = {0, 0};
        int[] coordinateOfHero = new int[2];
        String[][] map = mapVO.getMap();
        for(int i = 0; i < map.length; i++){
            for(int j = 0; j < map[0].length; j++){
                if(map[i][j].equals("H")){
                    coordinateOfHero[0] = j;
                    coordinateOfHero[1] = i;
                }
            }
        }

        String directionOfMoving = hero.getHeading();
        switch (directionOfMoving) {
            case "Eszak" -> {
                coordinateOfMoving[0] = coordinateOfHero[0];
                coordinateOfMoving[1] = coordinateOfHero[1] - 1;
            }
            case "Del" -> {
                coordinateOfMoving[0] = coordinateOfHero[0];
                coordinateOfMoving[1] = coordinateOfHero[1]+1;
            }
            case "Nyugat" -> {
                coordinateOfMoving[0] = coordinateOfHero[0]-1;
                coordinateOfMoving[1] = coordinateOfHero[1];
            }
            case "Kelet" -> {
                coordinateOfMoving[0] = coordinateOfHero[0]+1;
                coordinateOfMoving[1] = coordinateOfHero[1];
            }
        }
        boolean gold = hero.isHavingGold();
        int arrows = hero.getNumberOfArrows();
        String otherComponent = map[coordinateOfMoving[1]][coordinateOfMoving[0]];
        boolean coordinateCorrection = validators.IsTheCoordinateCorrect(coordinateOfMoving, mapVO.getMapSize());
        if (!coordinateCorrection) {
            messega = "Hősünk falba ütközött.";
        } else {
            switch (otherComponent) {
                case "_" -> {
                    if(coordinateOfMoving[0] == hero.getStartCoordinate()[0] && coordinateOfMoving[1] == hero.getStartCoordinate()[1] && gold){
                        player.setWinMeter(true);
                        game.setGameSwitcher(false);
                        messega = "Hősünk célba ért!";
                    }
                    String temp = "_";
                    map[coordinateOfMoving[1]][coordinateOfMoving[0]] = map[coordinateOfHero[1]][coordinateOfHero[0]];
                    map[coordinateOfHero[1]][coordinateOfHero[0]] = temp;
                    hero.setActualCoordinate(coordinateOfHero);
                    game.setStepIshappening(true);
                    messega = "Hősünk lépett egyet.";
                }
                case "P" -> {
                    if (arrows > 0) {
                        arrows -= 1;
                        hero.setNumberOfArrows(arrows);
                        messega = "Hősünk beleesett egy verembe. A veremből kijutáshoz felhasznált egy nyílvesszőt.";
                    } else {
                        game.setGameSwitcher(false);
                        player.setDeathMeter(true);
                        map[coordinateOfHero[0]][coordinateOfHero[1]] = "_";
                        messega = "Hősünk beleesett egy verembe. Mivel elfogytak a nyílvesszői, ezért a veremben ragadt. A hősünk meghalt.";
                    }
                }
                case "G" -> {
                    hero.setHavingGold(true);
                    String temp = "_";
                    map[coordinateOfMoving[1]][coordinateOfMoving[0]] = map[coordinateOfHero[1]][coordinateOfHero[0]];
                    map[coordinateOfHero[1]][coordinateOfHero[0]] = temp;
                    hero.setActualCoordinate(coordinateOfHero);
                    game.setStepIshappening(true);
                    messega = "Hősünk felszedte az aranyat.";
                }
                case "U" -> {
                    map[coordinateOfHero[0]][coordinateOfHero[1]] = "_";
                    game.setGameSwitcher(false);
                    player.setDeathMeter(true);
                    messega = "Hősünk találkozott egy wumpusszal, ami megette. Hősünk meghalt.";
                }
                case "W" -> messega = "Hősünk falba ütközött.";
            }
        }
        mapVO.setMap(map);
        return messega;
    }
}
