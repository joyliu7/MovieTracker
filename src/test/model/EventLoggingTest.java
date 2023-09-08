package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventLoggingTest {
    private MovieList testMovieList;
    private Movie testMovieOne;
    private Movie testMovieTwo;
    private Movie testMovieThree;
    private EventLog eventLog;

    @BeforeEach
    void runBefore() {
        eventLog = EventLog.getInstance();
        eventLog.clear();
        testMovieList = new MovieList();
        testMovieOne = new Movie("m1", true);
        testMovieTwo = new Movie("m2", false);
        testMovieThree = new Movie("m3", true);
    }

    @Test
    void testAddMovie() {
        testMovieList.addMovie(testMovieOne);
        testMovieList.addMovie(testMovieTwo);
        testMovieList.addMovie(testMovieThree);
        List descriptionList = new ArrayList<>();
        for (Event event : eventLog) {
            descriptionList.add(event.getDescription());
        }
        assertEquals(4, descriptionList.size());
        assertEquals("Event log cleared.", descriptionList.get(0));
        assertEquals("Added movie: \"m1\"", descriptionList.get(1));
        assertEquals("Added movie: \"m2\"", descriptionList.get(2));
        assertEquals("Added movie: \"m3\"", descriptionList.get(3));

    }

    @Test
    void testRemoveMovieAt() {
        testMovieList.addMovie(testMovieOne);
        testMovieList.addMovie(testMovieTwo);
        testMovieList.addMovie(testMovieThree);
        List descriptionList = new ArrayList<>();
        for (Event event : eventLog) {
            descriptionList.add(event.getDescription());
        }
        assertEquals(4, descriptionList.size());
        assertEquals("Event log cleared.", descriptionList.get(0));
        assertEquals("Added movie: \"m1\"", descriptionList.get(1));
        assertEquals("Added movie: \"m2\"", descriptionList.get(2));
        assertEquals("Added movie: \"m3\"", descriptionList.get(3));

        testMovieList.removeMovieAt(0);
        testMovieList.removeMovieAt(0);
        testMovieList.removeMovieAt(0);
        List descriptionListTwo = new ArrayList<>();
        for (Event event : eventLog) {
            descriptionListTwo.add(event.getDescription());
        }
        assertEquals(7, descriptionListTwo.size());
        assertEquals("Event log cleared.", descriptionListTwo.get(0));
        assertEquals("Added movie: \"m1\"", descriptionListTwo.get(1));
        assertEquals("Added movie: \"m2\"", descriptionListTwo.get(2));
        assertEquals("Added movie: \"m3\"", descriptionListTwo.get(3));
        assertEquals("Removed movie: \"m1\"", descriptionListTwo.get(4));
        assertEquals("Removed movie: \"m2\"", descriptionListTwo.get(5));
        assertEquals("Removed movie: \"m3\"", descriptionListTwo.get(6));
    }
}
