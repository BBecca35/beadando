CREATE TABLE player(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(255) NOT NULL,
    death_meter INT,
    win_meter INT,
    game_state INT

);

CREATE TABLE game_state(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    map_name VARCHAR(255) NOT NULL,
    map_size INT NOT NULL,
    map VARCHAR(1000) NOT NULL,
    player_id INT NOT NULL,
    FOREIGN KEY (player_id) REFERENCES player (id)
);

CREATE TABLE hero(
    id INT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    heading VARCHAR(255) NOT NULL,
    number_of_arrows INT NOT NULL,
    having_gold INT,
    first_coordinate INT NOT NULL,
    last_coordinate INT NOT NULL,
    game_state_id INT NOT NULL,
    FOREIGN KEY (game_state_id) REFERENCES game_state(id)
);
