package persistence;

import model.Movie;
import model.MovieList;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

// Tests the methods in JsonWriter class
// The code in this class is based on the code provided in JsonSerializationDemo project
class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFile() {
        try {
            MovieList movieList = new MovieList();
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyMovieList() {
        try {
            MovieList movieList = new MovieList();
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyMovieList.json");
            writer.open();
            writer.write(movieList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyMovieList.json");
            movieList = reader.read();
            assertEquals(0, movieList.sizeMovieList());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralMovieList() {
        try {
            MovieList movieList = new MovieList();
            movieList.addMovie(new Movie("Movie Three", false));
            movieList.addMovie(new Movie("Movie Four", true));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralMovieList.json");
            writer.open();
            writer.write(movieList);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralMovieList.json");
            movieList = reader.read();
            List<Movie> movies = movieList.getMovies();
            assertEquals(2, movies.size());
            checkMovie("Movie Three", false, movies.get(0));
            checkMovie("Movie Four", true, movies.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
