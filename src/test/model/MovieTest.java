package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

// Tests the methods in Movie class
public class MovieTest {
    private Movie testMovieOne;
    private Movie testMovieTwo;

    @BeforeEach
    void runBefore() {
        testMovieOne = new Movie("Interstellar", true);
        testMovieTwo = new Movie("The Great Gatsby", false);
    }

    @Test
    void testConstructor() {
        assertEquals("Interstellar", testMovieOne.getName());
        assertTrue(testMovieOne.getIsWatched());
        assertEquals("The Great Gatsby", testMovieTwo.getName());
        assertFalse(testMovieTwo.getIsWatched());
    }

    @Test
    void testGetIsWatchedYN() {
        assertEquals("Y", testMovieOne.getIsWatchedYN());
        assertEquals("N", testMovieTwo.getIsWatchedYN());
    }

    @Test
    void testSetIsWatched() {
        testMovieOne.setIsWatched(false);
        assertFalse(testMovieOne.getIsWatched());
    }

    @Test
    void testSetIsWatchedUnchanged() {
        testMovieOne.setIsWatched(true);
        assertTrue(testMovieOne.getIsWatched());
    }

    @Test
    void testSetIsWatchedDifferentMultipleTimes() {
        testMovieOne.setIsWatched(true);
        assertTrue(testMovieOne.getIsWatched());
        testMovieOne.setIsWatched(false);
        assertFalse(testMovieOne.getIsWatched());
    }

    @Test
    void testSetIsWatchedSameMultipleTimes() {
        testMovieOne.setIsWatched(false);
        assertFalse(testMovieOne.getIsWatched());
        testMovieOne.setIsWatched(false);
        assertFalse(testMovieOne.getIsWatched());
    }

}
