package hu.nye.home.ui.editor;

import hu.nye.home.data.GameStateRepo;
import hu.nye.home.model.GameStateModel;
import hu.nye.home.model.Hero;
import hu.nye.home.model.MapVO;
import hu.nye.home.model.Player;
import hu.nye.home.service.util.Utils;
import hu.nye.home.service.util.UtilsImp;
import hu.nye.home.service.validator.Validators;
import hu.nye.home.service.validator.ValidatorsImp;

import java.util.*;
import java.util.List;

public class EditorMenu {
    public static void edit(MapVO mapVO, Hero hero, GameStateRepo repo, String name, GameStateModel model, Player player){
        Validators validators = new ValidatorsImp();
        Scanner scanner = new Scanner(System.in);
        Utils utils = new UtilsImp();
        SortedMap<String, List<String>> components = new TreeMap<>();
        int numberOfChanges = 0;
        boolean mainSwitcher = true;
        System.out.println("Pályaszerkesztés kezdése: ");
        while(mainSwitcher) {
            try {
                System.out.println("-------------------------------");
                System.out.println(mapVO.toString());
                System.out.println("-------------------------------");
                System.out.println("Kérem adja meg az opció számát!");
                System.out.println("1. módosítás");
                System.out.println("2. visszavonás");
                System.out.println("3. törlés");
                System.out.println("4. A szerkesztő bezárása");
                System.out.println("\nAz opció száma: ");
                String option = scanner.nextLine();
                int optionInInt = Integer.parseInt(option);
                System.out.println("-------------------------------");
                if (optionInInt == 1) {
                    System.out.println("A módosítás megkezdése.");
                    ChangerMenu.change(mapVO, components, hero);
                    numberOfChanges += 1;
                } else if (optionInInt == 2) {
                    if(numberOfChanges == 0){
                        System.out.println("Hiba. Még nem történt módosítás a pályán, ezért a visszavonás még nem lehetséges.");
                    }else{
                        System.out.println("A visszavonás megkezdése.");
                        RemoverMenu.remover(components, mapVO);
                    }
                } else if (optionInInt == 3) {
                    System.out.println("A törlés megkezdése.");
                    DeleteMenu.delete(components, mapVO);
                } else if (optionInInt == 4) {
                    boolean numberOfHerosCheck = validators.applicableHeroOrGold(mapVO, "H");
                    boolean numberOfGoldsCheck = validators.applicableHeroOrGold(mapVO, "G");
                    boolean numberOfWumpusesCheck = validators.applicableWumpus(mapVO, "U");
                    if(numberOfWumpusesCheck && numberOfGoldsCheck && numberOfHerosCheck){
                        int arrows = utils.countWumpus(mapVO.getMapSize());
                        hero.setNumberOfArrows(arrows);
                        boolean switcher = true;
                        while (switcher){
                            try {
                                System.out.println("Szeretné menteni a pályát?.");
                                System.out.println("---------------------------");
                                System.out.println("1. igen");
                                System.out.println("2. nem");
                                System.out.println("\nA válasz száma:");
                                String answerInStr = scanner.nextLine();
                                int numberOfanswer = Integer.parseInt(answerInStr);
                                if(numberOfanswer == 1){
                                    boolean switcher2 = true;
                                    while(switcher2){
                                        System.out.println("Kérem, adja meg a pálya nevét!");
                                        String mapName = scanner.nextLine();
                                        if(mapName.isEmpty()){
                                            System.out.println("-------------------------------------------------");
                                            System.out.println("A pálya neve nem lehet üres. Kérem töltse ki!");
                                            System.out.println("-------------------------------------------------");
                                        }else {
                                            int mapIsExist = repo.findMapByName(mapName);
                                            if(mapIsExist != 0){
                                                System.out.println("-------------------------------------------------");
                                                System.out.println("Ez a pályanév már foglalt!");
                                                System.out.println("-------------------------------------------------");
                                            }
                                            else{
                                                String mapInStr = utils.convertMapVoMapToString(mapVO);
                                                int playerId = repo.findPlayerByName(name);
                                                model.setMapName(mapName);
                                                model.setMapSize(mapVO.getMapSize());
                                                model.setMap(mapInStr);
                                                model.setPlayerId(playerId);
                                                repo.saveGameState(model);
                                                int gameStateId = repo.findMapByName(mapName);
                                                hero.setGameStateID(gameStateId);
                                                repo.saveHero(hero);
                                                System.out.println("A pályaszerkesztő bezárása.");
                                                switcher2 = false;
                                                switcher = false;
                                                mainSwitcher = false;
                                            }
                                        }
                                    }
                                } else if (numberOfanswer == 2) {
                                    System.out.println("A pályaszerkesztő bezárása.");
                                    switcher = false;
                                    mainSwitcher = false;
                                } else if (numberOfanswer > 2) {
                                    System.out.println("Hiba. Az adott számon funkció nem található.");
                                }

                            }catch (Exception e){
                                System.out.println("Hiba. Nem számot adott meg. Kérem, töltse ki helyesen a mezőt!");
                            }
                        }
                    }
                    else {
                        System.out.println("A pályaszerkesztő még nem zárható be, mert valamelyik elemből nincs elég vagy több van a pályán a megengedettnél.");
                    }
                } else {
                    System.out.println("Hiba. Az adott számon funkció nem található.");
                }
            }catch (Exception e){
                System.out.println("Hiba. Nem számot adott meg. Kérem, töltse ki helyesen a mezőt!");
            }
        }
    }
}
