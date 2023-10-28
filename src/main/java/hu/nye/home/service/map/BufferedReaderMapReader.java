package hu.nye.home.service.map;

import hu.nye.home.service.exceptions.MapReadException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BufferedReaderMapReader implements MapReader{

    private final BufferedReader bufferedReader;

    public BufferedReaderMapReader(BufferedReader bufferedReader){
        this.bufferedReader = bufferedReader;
    }

    @Override
    public List<String> readMap() throws MapReadException {

        List<String> result = new ArrayList<>();
        String line;
        InputStream inputStream = null;
        StringBuilder sb = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null){
                sb.append(Arrays.toString(line.split(",")));
                result.add(line);
            }
        } catch (IOException e) {
            throw new MapReadException("Failed to read map");

        }


        return result;
    }
}
