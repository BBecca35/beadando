package hu.nye.home.service.map;

import hu.nye.home.service.exceptions.MapReadException;

import java.util.List;

public interface MapReader {

    List<String> readMap() throws MapReadException;
}
