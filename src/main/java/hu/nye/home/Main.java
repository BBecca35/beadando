package hu.nye.home;

/*import hu.nye.home.model.Hero;
import hu.nye.home.service.map.BufferedReaderMapReader;
import hu.nye.home.service.map.MapReader;
import hu.nye.home.service.map.DataFromMapImp;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
*/

import hu.nye.home.model.Hero;
import hu.nye.home.ui.MainMenu;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args){
        MainMenu menu = new MainMenu();
        menu.runTheProgram();
    }
}