package hu.nye.home.ui;

import java.util.List;

public interface MapConverter {
    String[][] convertMap(List<String> inputlist);
    void printMap(String[][] input);
}
