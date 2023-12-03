package hu.nye.home.ui;

import hu.nye.home.data.GameStateRepo;
import hu.nye.home.data.GameStateRepoImp;
import hu.nye.home.model.GameStateModel;
import hu.nye.home.model.Hero;
import hu.nye.home.model.MapVO;
import hu.nye.home.model.Player;
import hu.nye.home.service.util.Utils;
import hu.nye.home.service.util.UtilsImp;
import hu.nye.home.ui.game.GameMenu;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

import static hu.nye.home.ui.editor.EditorMenu.edit;

public class MainMenu {

    Logger logger = LoggerFactory.getLogger(MainMenu.class);
    private String userName;
    private Player player;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public Player getPlayer() {
        return player;
    }
    public void setPlayer(Player player) {
        this.player = player;
    }
    public MainMenu() {
    }

    public void runTheProgram() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/wumpus", "root", "");
            GameStateRepo repo = new GameStateRepoImp(connection);
            MainMenu mainMenu = new MainMenu();
            GameMenu menu = new GameMenu();
            GameStateModel model = new GameStateModel();
            Hero hero = new Hero(false);
            Utils utils = new UtilsImp();
            Scanner scanner = new Scanner(System.in);
            boolean switcher1 = true;
            while (switcher1) {
                System.out.println("Felhasználónév: ");
                mainMenu.setUserName(scanner.nextLine());
                if (mainMenu.getUserName().isEmpty()) {
                    System.out.println("-------------------------------");
                    System.out.println("A Felhasználónevet nem lehet üresen hagyni.");
                    System.out.println("Kérem töltse ki!");
                    System.out.println("-------------------------------");
                } else {
                    int nameIsExist = repo.findPlayerByName(mainMenu.getUserName());
                    if(nameIsExist == 0){
                        mainMenu.setPlayer(new Player(mainMenu.getUserName(), false, false, 0));
                        repo.savePlayer(mainMenu.getPlayer());
                        System.out.println("-------------------------------");
                        System.out.println("Az új felhasználó mentése megtörtént.");
                        System.out.println("-------------------------------");
                    }
                    else{
                        mainMenu.setPlayer(repo.loadPlayer(mainMenu.getUserName()));
                        System.out.println("-------------------------------");
                        System.out.println("A felhasználó már létezik, a betöltése megtörtént.");
                        System.out.println("-------------------------------");
                    }
                    switcher1 = false;
                }
            }
            boolean switcher2 = true;
            while (switcher2) {
                try {
                    System.out.println("Menü:");
                    System.out.println("-------------------------------");
                    System.out.println("Kérem írja be az opció számát!");
                    System.out.println("1. Játék");
                    System.out.println("2. Pályaszerkesztés");
                    System.out.println("3. Bezárás");
                    System.out.println("\nAz opció száma: ");
                    String option = scanner.nextLine();
                    int optionInInt = Integer.parseInt(option);
                    System.out.println("-------------------------------");
                    if (optionInInt == 1) {
                        menu.game(mainMenu.getPlayer(), repo);
                    } else if (optionInInt == 2) {
                        boolean switcher3 = true;
                        while (switcher3) {
                            try {
                                System.out.println("Kérem, adja meg a pálya méretét! ");
                                String mapSize = scanner.nextLine();
                                int mapSizeInInt = Integer.parseInt(mapSize);
                                if (mapSizeInInt < 6) {
                                    System.out.println("A pálya mérete nem lehet kissebb, mint 6.");
                                } else if (mapSizeInInt > 20) {
                                    System.out.println("A pálya mérete nem lehet nagyobb, mint 20");
                                } else {
                                    String[][] newMap = utils.createMap(mapSizeInInt);
                                    MapVO changed = new MapVO(mapSizeInInt, newMap);
                                    edit(changed, hero, repo, mainMenu.getUserName(), model, mainMenu.getPlayer());
                                    switcher3 = false;
                                }
                            } catch (Exception e) {
                                System.out.println("Hiba, rossz érték. Kérem, töltse ki helyesen a mezőt!");
                            }
                        }
                    } else if (optionInInt == 3) {
                        System.out.println("A Program leáll.");
                        scanner.close();
                        connection.close();
                        switcher2 = false;
                    } else {
                        System.out.println("Ilyen számmal ellátott funkció nem létezik.");
                    }
                } catch (Exception e) {
                    System.out.println("Hiba, nem számot adott meg. Kérem, töltse ki helyesen a mezőt!");
                }
            }
            } catch (SQLException | ClassNotFoundException exception) {
                logger.error("Unexpected error even during connect to the database.", exception);
        }
    }
}

