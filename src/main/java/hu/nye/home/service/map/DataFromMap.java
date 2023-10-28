package hu.nye.home.service.map;

import java.util.List;

public interface DataFromMap {
    String[][] convertMap(List<String> inputlist1);
    String[] splitMap(List<String> inputlist2);
    int wumpuszCounter(List<String> inputlist2);
}
