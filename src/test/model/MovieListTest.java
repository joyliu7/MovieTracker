package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

// Tests the methods in MovieList class
public class MovieListTest {
    private MovieList testMovieList;
    private Movie movieOne;
    private Movie movieTwo;
    private Movie movieThree;

    @BeforeEach
    void runBefore() {
        testMovieList = new MovieList();
        movieOne = new Movie("Interstellar", true);
        movieTwo = new Movie("The Great Gatsby", false);
        movieThree = new Movie("Inception", true);
    }

    @Test
    void testConstructor() {
        assertNotNull(testMovieList.getMovies());
        assertEquals(0, testMovieList.getMovies().size());
    }

    @Test
    void testAddMovie() {
        testMovieList.addMovie(movieOne);
        assertEquals(1, testMovieList.getMovies().size());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
    }

    @Test
    void testAddMultipleMovies() {
        testMovieList.addMovie(movieOne);
        assertEquals(1, testMovieList.getMovies().size());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        testMovieList.addMovie(movieTwo);
        assertEquals(2, testMovieList.getMovies().size());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
    }

    @Test
    void testSizeMovieList() {
        assertEquals(0, testMovieList.sizeMovieList());
    }

    @Test
    void testSizeMovieListSizeOne() {
        testMovieList.addMovie(movieOne);
        assertEquals(1, testMovieList.sizeMovieList());
    }

    @Test
    void testSizeMovieListSizeTwo() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        assertEquals(2, testMovieList.sizeMovieList());
    }

    @Test
    void testRemoveMovieIf() {
        testMovieList.removeMovieIf("Interstellar");
        assertNotNull(testMovieList.getMovies());
        assertEquals(0, testMovieList.sizeMovieList());
    }

    @Test
    void testRemoveMovieIfRemoveFirst() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        assertEquals(2, testMovieList.sizeMovieList());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
        testMovieList.removeMovieIf("Interstellar");
        assertEquals(1, testMovieList.sizeMovieList());
        assertEquals(movieTwo, testMovieList.getMovies().get(0));
    }

    @Test
    void testRemoveMovieIfRemoveSecond() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        assertEquals(2, testMovieList.sizeMovieList());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
        testMovieList.removeMovieIf("The Great Gatsby");
        assertEquals(1, testMovieList.sizeMovieList());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
    }

    @Test
    void testRemoveMovieIfRemoveAll() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        assertEquals(2, testMovieList.sizeMovieList());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
        testMovieList.removeMovieIf("Interstellar");
        testMovieList.removeMovieIf("The Great Gatsby");
        assertEquals(0, testMovieList.sizeMovieList());
        assertNotNull(testMovieList.getMovies());
    }

    @Test
    void testRemoveMovieIfRemoveNone() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        assertEquals(2, testMovieList.sizeMovieList());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
        testMovieList.removeMovieIf("Tenet");
        assertEquals(2, testMovieList.sizeMovieList());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
    }

    @Test
    void testRemoveMovieIfRemoveSameMovieMultipleTimes() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        assertEquals(2, testMovieList.sizeMovieList());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
        testMovieList.removeMovieIf("The Great Gatsby");
        assertEquals(1, testMovieList.sizeMovieList());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        testMovieList.removeMovieIf("The Great Gatsby");
        assertEquals(1, testMovieList.sizeMovieList());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
    }

    @Test
    void testGetMovies() {
        assertEquals(0, testMovieList.getMovies().size());
        assertNotNull(testMovieList.getMovies());
    }

    @Test
    void testGetMoviesMultipleMovies() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        assertEquals(2, testMovieList.getMovies().size());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
    }

    @Test
    void testRemoveMovieAtFirst() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        testMovieList.addMovie(movieThree);
        assertEquals(3, testMovieList.getMovies().size());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
        assertEquals(movieThree, testMovieList.getMovies().get(2));
        testMovieList.removeMovieAt(0);
        assertEquals(2, testMovieList.getMovies().size());
        assertEquals(movieTwo, testMovieList.getMovies().get(0));
        assertEquals(movieThree, testMovieList.getMovies().get(1));
    }

    @Test
    void testRemoveMovieAtLast() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        testMovieList.addMovie(movieThree);
        assertEquals(3, testMovieList.getMovies().size());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
        assertEquals(movieThree, testMovieList.getMovies().get(2));
        testMovieList.removeMovieAt(2);
        assertEquals(2, testMovieList.getMovies().size());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
    }

    @Test
    void testRemoveMovieAtMiddle() {
        testMovieList.addMovie(movieOne);
        testMovieList.addMovie(movieTwo);
        testMovieList.addMovie(movieThree);
        assertEquals(3, testMovieList.getMovies().size());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieTwo, testMovieList.getMovies().get(1));
        assertEquals(movieThree, testMovieList.getMovies().get(2));
        testMovieList.removeMovieAt(1);
        assertEquals(2, testMovieList.getMovies().size());
        assertEquals(movieOne, testMovieList.getMovies().get(0));
        assertEquals(movieThree, testMovieList.getMovies().get(1));
    }

}
