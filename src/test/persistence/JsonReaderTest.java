package persistence;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests the methods in JsonReader class
// The code in this class is based on the code provided in JsonSerializationDemo project
class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFile() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            MovieList movieList = reader.read();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyMovieList.json");
        try {
            MovieList movieList = reader.read();
            assertEquals(0, movieList.sizeMovieList());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralMovieList() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralMovieList.json");
        try {
            MovieList movieList = reader.read();
            List<Movie> movies = movieList.getMovies();
            assertEquals(2, movies.size());
            checkMovie("Movie One", true, movies.get(0));
            checkMovie("Movie Two", false, movies.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}