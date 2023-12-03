package hu.nye.home.data;

import hu.nye.home.model.GameStateModel;
import hu.nye.home.model.Hero;
import hu.nye.home.model.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class GameStateRepoImp implements GameStateRepo {

    Logger logger = LoggerFactory.getLogger(GameStateRepoImp.class);
    private Connection connection;
    private final String updateHeroQuery = "UPDATE hero " +
            "SET heading = ?, " +
            "number_of_arrows = ?, " +
            "having_gold = ?, " +
            "first_start_coordinate = ?, " +
            "last_start_coordinate = ?, " +
            "WHERE id = ?";
    private final String updateGameStateOfPlayerQuery = "UPDATE player " +
            "SET game_state = ? WHERE id = ?";
    private final String updatePlayerQuery = "UPDATE player " +
            "SET death_meter = ?, win_meter = ? WHERE id = ?";
    private final String updateGameStateQuery = "UPDATE game_state SET map = ? " +
            "WHERE id = ?";
    private final String saveHeroQuery = "INSERT INTO " +
            "hero (heading, " +
            "number_of_arrows, " +
            "having_gold, " +
            "first_start_coordinate, " +
            "last_start_coordinate, " +
            "game_state_id) " +
            "VALUES (?,?,?,?,?,?);";
    private final String loadHeroQuery = "SELECT * FROM hero " +
            "WHERE game_state_id = ?;";
    private final String savePlayerQuery = "INSERT INTO " +
            "player (name, " +
            "win_meter, " +
            "death_meter, " +
            "game_state) " +
            "VALUES (?,?,?,?);";
    private final String loadPlayerQuery = "SELECT * FROM player WHERE name = ?;";
    private final String saveGameStateQuery = "INSERT INTO " +
            "game_state (map_name, " +
            "map_size, " +
            "map, " +
            "player_id " +
            "VALUES (?,?,?,?);";
    private final String loadAllGameStateOfPlayerQuery = "SELECT * FROM game_state " +
            "WHERE player_id = ?;";
    private final String findMapByNameQuery = "SELECT * FROM game_state WHERE map_name = ?;";
    private final String findPlayerByNameQuery = "SELECT * FROM player WHERE name = ?;";
    public GameStateRepoImp(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void saveHero(Hero hero) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(saveHeroQuery)) {
            preparedStatement.setString(1, hero.getHeading());
            preparedStatement.setInt(2, hero.getNumberOfArrows());
            int havingGold = hero.isHavingGold() ? 1 : 0;
            preparedStatement.setInt(3, havingGold);
            preparedStatement.setInt(4, hero.getStartCoordinate()[0]);
            preparedStatement.setInt(5, hero.getStartCoordinate()[1]);
            preparedStatement.setInt(6, hero.getGameStateID());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unexpected error even during save the hero.", e);
        }
    }

    @Override
    public Hero loadHero(int id) {
        Hero hero = new Hero();
        try (PreparedStatement preparedStatement = connection.prepareStatement(loadHeroQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                hero.setId(resultSet.getInt("id"));
                hero.setHeading(resultSet.getString("heading"));
                hero.setNumberOfArrows(resultSet.getInt("number_of_arrows"));
                hero.setHavingGold(resultSet.getInt("having_gold") == 1);
                int[] startCoordinate = new int[2];
                startCoordinate[0] = resultSet.getInt("first_start_coordinate");
                startCoordinate[1] = resultSet.getInt("last_start_coordinate");
                hero.setStartCoordinate(startCoordinate);
                int[] actualCoordinate = new int[2];
                actualCoordinate[0] = resultSet.getInt("first_start_coordinate");
                actualCoordinate[1] = resultSet.getInt("last_start_coordinate");
                hero.setActualCoordinate(actualCoordinate);
                hero.setGameStateID(resultSet.getInt("game_state_id"));

            }
        } catch (SQLException e) {
            logger.error("Unexpected error even during load the hero.", e);
        }
        return hero;
    }

    @Override
    public void updateHero(int id, Hero hero) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(updateHeroQuery)) {
            preparedStatement.setString(1, hero.getHeading());
            preparedStatement.setInt(2, hero.getNumberOfArrows());
            int havingGold = hero.isHavingGold() ? 1 : 0;
            preparedStatement.setInt(3, havingGold);
            preparedStatement.setInt(4, hero.getStartCoordinate()[0]);
            preparedStatement.setInt(5, hero.getStartCoordinate()[1]);
            preparedStatement.setInt(6, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unexpected error even during update the hero.", e);
        }
    }

    @Override
    public void savePlayer(Player player) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(savePlayerQuery)) {
            preparedStatement.setString(1, player.getName());
            preparedStatement.setInt(2, player.getGameState());
            preparedStatement.setInt(3, player.isDeathMeter()?1:0);
            preparedStatement.setInt(4, player.isWinMeter()?1:0);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unexpected error even during save the player.", e);
        }
    }
    @Override
    public int findPlayerByName(String name) {
        int playerId = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(findPlayerByNameQuery)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                playerId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Unexpected error even during find the player by name.", e);
        }
        return playerId;
    }

    @Override
    public Player loadPlayer(String name) {
        Player player = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement(loadPlayerQuery)) {
            preparedStatement.setString(1, name);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("id");
                String nameOfPlayer = resultSet.getString("name");
                boolean deathMeter = resultSet.getInt("death_meter") == 1;
                boolean winMeter = resultSet.getInt("win_meter") == 1;
                int gameState = resultSet.getInt("game_state");
                player = new Player(id, nameOfPlayer,deathMeter, winMeter, gameState);
            }
        } catch (SQLException e) {
            logger.error("Unexpected error even during load the Player.", e);
        }return player;
    }

    @Override
    public void updateGameStateOfPlayer(int id, int gameState) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(updateGameStateOfPlayerQuery)) {
            preparedStatement.setInt(1, gameState);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unexpected error even during update game_state of player.", e);
        }
    }

    @Override
    public void updatePlayer(int id, Player player) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(updatePlayerQuery)) {
            preparedStatement.setInt(1, player.isDeathMeter()?1:0);
            preparedStatement.setInt(2, player.isWinMeter()?1:0);
            preparedStatement.setInt(3, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unexpected error even during update the player.", e);
        }
    }

    @Override
    public List<GameStateModel> loadAllGameStateOfPlayerFromDatabase(int id) {
        List<GameStateModel> gameStateModels = new ArrayList<>();
        try(PreparedStatement preparedStatement = connection.prepareStatement(loadAllGameStateOfPlayerQuery)) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                int gameStateId = resultSet.getInt("id");
                String name = resultSet.getString("map_name");
                int size = resultSet.getInt("map_size");
                String map = resultSet.getString("map");
                int playerId = resultSet.getInt("player_id");
                gameStateModels.add(new GameStateModel(gameStateId, name, size ,map, playerId));
            }

        } catch (SQLException e) {
            logger.error("Unexpected error even during load all Game State.", e);
        }
        return gameStateModels;
    }
    @Override
    public void saveGameState(GameStateModel model) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(saveGameStateQuery)) {
            preparedStatement.setString(1, model.getMapName());
            preparedStatement.setInt(2, model.getMapSize());
            preparedStatement.setString(3, model.getMap());
            preparedStatement.setInt(4, model.getPlayerId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unexpected error even during save the Game State.", e);
        }
    }

    @Override
    public void updateGameState(int id, String map) {
        try(PreparedStatement preparedStatement = connection.prepareStatement(updateGameStateQuery)) {
            preparedStatement.setString(1, map);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.error("Unexpected error even during update the Game State.", e);
        }
    }

    @Override
    public int findMapByName(String mapName) {
        int mapId = 0;
        try(PreparedStatement preparedStatement = connection.prepareStatement(findMapByNameQuery)) {
            preparedStatement.setString(1, mapName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if(resultSet.next()){
                mapId = resultSet.getInt("id");
            }
        } catch (SQLException e) {
            logger.error("Unexpected error even during find the map by name.", e);
        }
        return mapId;

    }
}
