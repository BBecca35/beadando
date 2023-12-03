package hu.nye.home.data;

import hu.nye.home.model.GameStateModel;
import hu.nye.home.model.Hero;
import hu.nye.home.model.Player;

import java.util.List;

public interface GameStateRepo {
    void saveHero(Hero hero);
    Hero loadHero(int id);
    void updateHero(int id, Hero hero);
    void savePlayer(Player player);
    int findPlayerByName(String name);
    Player loadPlayer(String name);
    void updateGameStateOfPlayer(int id, int gameState);
    void updatePlayer(int id, Player player);
    List<GameStateModel> loadAllGameStateOfPlayerFromDatabase(int id);
    void saveGameState(GameStateModel model);
    void updateGameState(int id, String map);
    int findMapByName(String mapName);

}
