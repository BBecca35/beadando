package hu.nye.home.ui.game;

import hu.nye.home.data.GameStateRepo;
import hu.nye.home.model.GameStateModel;
import hu.nye.home.model.Hero;
import hu.nye.home.model.MapVO;
import hu.nye.home.model.Player;
import hu.nye.home.service.commands.ArrowShooting;
import hu.nye.home.service.commands.HeroMoving;
import hu.nye.home.service.util.*;

import java.util.Scanner;

public class Game {
    private boolean stepIshappening;
    private boolean gameSwitcher;
    private boolean heroOnTheMap;

    public Game(boolean stepIshappening, boolean gameSwitcher, boolean heroOnTheMap) {
        this.stepIshappening = stepIshappening;
        this.gameSwitcher = gameSwitcher;
        this.heroOnTheMap = heroOnTheMap;
    }

    public Game() {

    }

    public boolean isStepIshappening() {
        return stepIshappening;
    }
    public void setStepIshappening(boolean stepIshappening) {
        this.stepIshappening = stepIshappening;
    }
    public boolean isGameSwitcher() {
        return gameSwitcher;
    }
    public void setGameSwitcher(boolean gameSwitcher) {
        this.gameSwitcher = gameSwitcher;
    }
    public boolean isHeroOnTheMap() {
        return heroOnTheMap;
    }

    public void setHeroOnTheMap(boolean heroOnTheMap) {
        this.heroOnTheMap = heroOnTheMap;
    }

    public static void runTheGame(MapVO mapVO, Hero hero, Player player, GameStateModel model, GameStateRepo repo, GameMenu menu){
        Utils utils = new UtilsImp();
        GameSaving savingUtil = new GameSaving();
        HeroMoving heroMoving = new HeroMoving();
        Game game = new Game(false, true, true);
        int numberOfSteps = 0;
        Scanner scanner = new Scanner(System.in);
        for(int i = 0; i < hero.getActualCoordinate().length; i++){
            System.out.println(hero.getActualCoordinate()[i]);
        }
        System.out.println("A játék indítása...");
        while (game.isGameSwitcher()) {
            try {
                System.out.println("-------------------------------");
                System.out.println(mapVO.toString());
                System.out.println("-------------------------------");
                System.out.println("A hősünk jelenleg ebbe az irányba néz: " + hero.getHeading());
                System.out.println("A hősünk nyílvesszőinek száma: " + hero.getNumberOfArrows());
                System.out.println("\n-------------------------------");
                System.out.println("Kérem adja meg az opció számát!");
                System.out.println("-------------------------------");
                System.out.println("1. Lépés");
                System.out.println("2. Fordulás");
                System.out.println("3. Lövés");
                System.out.println("4. A játékmenet mentése");
                System.out.println("5. A játék feladása");
                System.out.println("\nAz opció száma: ");
                String option = scanner.nextLine();
                System.out.println("-------------------------------");
                int option1InInt = Integer.parseInt(option);
                if (option1InInt == 1) {
                    String messega1 = heroMoving.moveHero(hero, mapVO, player, game);
                    if (game.isStepIshappening()) {
                        numberOfSteps += 1;
                    }
                    System.out.println(messega1);
                } else if (option1InInt == 2) {
                    boolean switcher1 = true;
                    while (switcher1) {
                        try {
                            System.out.println("Kérem, Adja meg a hős új irányát.");
                            System.out.println("------------------------------------");
                            System.out.println("1. észak");
                            System.out.println("2. dél");
                            System.out.println("3. kelet");
                            System.out.println("4. nyugat");
                            System.out.println("\nAz irány száma: ");
                            System.out.println("------------------------------------");
                            String numberOfDirectionInStr = scanner.nextLine();
                            int numberOfDirection = Integer.parseInt(numberOfDirectionInStr);
                            String direction = utils.convertNumberToDirection(numberOfDirection);
                            if (direction != null && numberOfDirection <= 4) {
                                hero.setHeading(direction);
                                System.out.println("A hősünk most már ebbe az irányba néz: " + hero.getHeading());
                                switcher1 = false;
                            } else {
                                System.out.println("Az adott számmal ellálott irány nem létezik.");
                            }
                        } catch (Exception e) {
                            System.out.println("Hiba, nem számot adott meg. Kérem, töltse ki helyesen a mezőt!");
                        }
                    }
                } else if (option1InInt == 3) {
                    String messega2 = null;
                    if(hero.getNumberOfArrows() == 0){
                        messega2 = "A nyílvesszők elfogytak.";
                    }
                    else {
                        messega2 = ArrowShooting.shootArrow(mapVO, hero);
                    }
                    System.out.println(messega2);
                } else if (option1InInt == 4) {
                    System.out.println("A játékállás mentésének megkezdése.");
                    String map = utils.convertMapVoMapToString(mapVO);
                    int playerId = player.getId();
                    int gameStateId = model.getId();
                    int heroId = hero.getId();
                    hero.setStartCoordinate(hero.getActualCoordinate());
                    repo.updateHero(heroId, hero);
                    repo.updateGameState(gameStateId, map);
                    repo.updatePlayer(playerId, player);
                    System.out.println("A mentés megtörént.");
                    menu.setMainSwitcher(false);
                } else if (option1InInt == 5) {
                    boolean switcher = true;
                    while (switcher) {
                        try {
                            System.out.println("Biztos, hogy fel szeretné adni?");
                            System.out.println("------------------------------------");
                            System.out.println("1. igen");
                            System.out.println("2. nem");
                            System.out.println("\nA Válasz száma: ");
                            System.out.println("------------------------------------");
                            String numberOfAnswerInStr = scanner.nextLine();
                            int numberOfAnswer = Integer.parseInt(numberOfAnswerInStr);
                            if (numberOfAnswer == 1) {
                                System.out.println("A játék feladása.");
                                game.setGameSwitcher(false);
                                switcher = false;
                            } else if (numberOfAnswer == 2) {
                                System.out.println("A játék folytatása");
                                switcher = false;
                            } else {
                                System.out.println("Az alábbi szám alatt funkció nem létezik.");
                            }
                        } catch (Exception e) {
                            System.out.println("Hiba, nem számot adott meg. Kérem, töltse ki helyesen a mezőt!");
                        }
                    }
                } else {
                    System.out.println("Az alábbi szám alatt funkció nem létezik.");
                }
            } catch (Exception e) {
                System.out.println("Hiba, nem számot adott meg. Kérem, töltse ki helyesen a mezőt!");
            }
        }
        if (player.isWinMeter()) {
            System.out.println("Gratulálok, nyertél! A játékot " + numberOfSteps + " lépésből játszottad ki.");
            savingUtil.saveTheGame(mapVO, hero, player, model, repo, menu);
            System.out.println("Kilépés a főmenübe.");
            menu.setMainSwitcher(false);

        } else if (player.isDeathMeter()) {
            System.out.println("A játéknak vége. Vesztettél!");
            savingUtil.saveTheGame(mapVO, hero, player, model, repo, menu);
            System.out.println("Kilépés a főmenübe.");
            menu.setMainSwitcher(false);
        }
    }
}


