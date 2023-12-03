package hu.nye.home.ui.game;

import hu.nye.home.data.GameStateRepo;
import hu.nye.home.model.GameStateModel;
import hu.nye.home.model.Hero;
import hu.nye.home.model.MapVO;
import hu.nye.home.model.Player;
import hu.nye.home.service.util.Utils;
import hu.nye.home.service.util.UtilsImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Scanner;

public class GameMenu {

    Logger logger = LoggerFactory.getLogger(GameMenu.class);
    private boolean mainSwitcher;
    public GameMenu(boolean mainSwitcher) {
        this.mainSwitcher = mainSwitcher;
    }
    public GameMenu() {
    }
    public boolean isMainSwitcher() {
        return mainSwitcher;
    }
    public void setMainSwitcher(boolean mainSwitcher) {
        this.mainSwitcher = mainSwitcher;
    }

    public void game(Player player, GameStateRepo repo){
        Utils utils = new UtilsImp();
        GameMenu menu = new GameMenu(true);
        Scanner scanner = new Scanner(System.in);
        while (menu.isMainSwitcher()){
            try {
                System.out.println("Kérem adja meg az opció számát!");
                System.out.println("-------------------------------");
                System.out.println("1. Pálya betöltése");
                System.out.println("2. Kilépés a főmenübe");
                System.out.println("\nAz opció száma: ");
                String option1 = scanner.nextLine();
                System.out.println("-------------------------------");
                int option1InInt = Integer.parseInt(option1);
                if(option1InInt == 1){
                    if(player.getGameState() == 0){
                        System.out.println("Ehhez a felhasználónévhez még nem tartozik pálya!");
                    }
                    else {
                        System.out.println("A pálya betöltésének megkezdése.");
                        List<GameStateModel> gameStateModels;
                        gameStateModels = repo.loadAllGameStateOfPlayerFromDatabase(player.getId());
                        boolean switcher1 = true;
                        while (switcher1) {
                            try {
                                System.out.println("Melyik pályát szeretné betölteni?");
                                System.out.println("---------------------------------");
                                for (int i = 0; i < gameStateModels.size(); i++) {
                                    String mapName = gameStateModels.get(i).getMapName();
                                    int number = gameStateModels.get(i).getId();
                                    System.out.println(i+1 + ". " + mapName);
                                }
                                System.out.println("\nA pálya száma: ");
                                String option2 = scanner.nextLine();
                                System.out.println("-------------------------------");
                                int option2InInt = Integer.parseInt(option2);
                                if (option2InInt <= gameStateModels.size()) {
                                    String[][] map = utils.convertStringToArray(gameStateModels.get(option2InInt-1));
                                    MapVO mapVo = new MapVO(gameStateModels.get(option2InInt-1).getMapSize(), map);
                                    Hero hero = repo.loadHero(gameStateModels.get(option2InInt-1).getId());
                                    GameStateModel model = gameStateModels.get(option2InInt-1);
                                    switcher1 = false;
                                    Game.runTheGame(mapVo, hero, player, model, repo, menu);
                                } else {
                                    System.out.println("Az alábbi szám alatt funkció nem létezik.");
                                }
                            } catch (Exception e) {
                                logger.error("Error", e);
                            }
                        }
                    }
                } else if (option1InInt == 2) {
                    menu.setMainSwitcher(false);
                } else{
                    System.out.println("Az alábbi szám alatt funkció nem létezik.");
                }

            }catch (Exception e){
                System.out.println("Hiba, nem számot adott meg. Kérem, töltse ki helyesen a mezőt!");
            }
        }
    }
}
