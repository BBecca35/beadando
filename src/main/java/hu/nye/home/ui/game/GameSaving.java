package hu.nye.home.ui.game;

import hu.nye.home.data.GameStateRepo;
import hu.nye.home.model.GameStateModel;
import hu.nye.home.model.Hero;
import hu.nye.home.model.MapVO;
import hu.nye.home.model.Player;
import hu.nye.home.service.util.Utils;
import hu.nye.home.service.util.UtilsImp;

import java.util.Scanner;

public class GameSaving {

    public GameSaving() {
    }
    public void saveTheGame(MapVO mapVO, Hero hero, Player player, GameStateModel model, GameStateRepo repo, GameMenu menu){
        Scanner scanner = new Scanner(System.in);
        Utils utils = new UtilsImp();
        boolean switcher2 = true;
        while(switcher2){
            try {
                System.out.println("Szeretné menteni a játékállást?");
                System.out.println("------------------------------------");
                System.out.println("1. igen");
                System.out.println("2. nem");
                System.out.println("\nA Válasz száma: ");
                System.out.println("------------------------------------");
                String numberOfAnswerInStr = scanner.nextLine();
                int answer = Integer.parseInt(numberOfAnswerInStr);
                if(answer == 1){
                    String map = utils.convertMapVoMapToString(mapVO);
                    int playerId = player.getId();
                    int gameStateId = model.getId();
                    int heroId = hero.getId();
                    repo.updateHero(heroId, hero);
                    repo.updateGameState(gameStateId, map);
                    repo.updatePlayer(playerId, player);
                    System.out.println("A mentés megtörént.");
                    switcher2 = false;
                    menu.setMainSwitcher(false);
                } else if (answer == 2) {
                    switcher2 = false;
                }
                else{
                    System.out.println("Az alábbi szám alatt funkció nem létezik.");
                }
            }catch(Exception e){
                System.out.println("Hiba, nem számot adott meg. Kérem, töltse ki helyesen a mezőt!");
            }
            switcher2 = false;
        }
    }
}
