package hu.nye.home.service.map;

import hu.nye.home.service.exceptions.MapReadException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doThrow;

@ExtendWith(MockitoExtension.class)
public class BufferedReaderMapReaderTest {

    private static final String LINE_1 = "Line 1";
    private static final String LINE_2 = "Line 2";
    private BufferedReaderMapReader underTest;
    @Mock
    private BufferedReader bufferedReaderMock;

    @BeforeEach
    public void setUp(){
        underTest = new BufferedReaderMapReader(bufferedReaderMock);
    }

    @Test
    void testReadMapShouldReturnRowsReadFromBufferedReader() throws IOException, MapReadException {
        given(bufferedReaderMock.readLine()).willReturn("Line 1", "Line 2", null);
        List<String> result = underTest.readMap();
        assertEquals(List.of(LINE_1,LINE_2), result);
    }

    @Test
    void testReadMapWhenEncountersIOExceptionWillThrowCustomException() throws IOException {
        doThrow(IOException.class).when(bufferedReaderMock).readLine();
        assertThrows(MapReadException.class, () -> {
            underTest.readMap();
        });
    }
}