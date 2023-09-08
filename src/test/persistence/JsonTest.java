package persistence;

import model.Movie;

import static org.junit.jupiter.api.Assertions.assertEquals;

// The superclass of JsonReaderTest class and JsonWriterTest class;
// provides a checkMovie method that can be used in these two subclasses
// The code in this class is based on the code provided in JsonSerializationDemo project
public class JsonTest {
    protected void checkMovie(String name, boolean isWatched, Movie movie) {
        assertEquals(name, movie.getName());
        assertEquals(isWatched, movie.getIsWatched());
    }
}
