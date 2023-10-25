package hu.nye.home.service.map;

import hu.nye.home.service.map.exceptions.MapReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class BufferedReaderMapReader implements MapReader{

    private final BufferedReader bufferedReader;

    public BufferedReaderMapReader(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    @Override
    public List<String> readMap() throws MapReadException {

        List<String> result = new ArrayList<>();
        InputStream inputStream = null;
        try {

            while (bufferedReader.readLine() != null){
                result.add(bufferedReader.readLine());
            }
        } catch (IOException e) {
            throw new MapReadException("Failed to read map");

        }


        return result;
    }
}
