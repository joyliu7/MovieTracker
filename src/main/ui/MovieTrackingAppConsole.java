package ui;

import model.Movie;
import model.MovieList;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// Represents a movie tracking application
// Part of the code in this class is based on
//      the code in the TellerApp class of the TellerApp project
public class MovieTrackingAppConsole {
    private static final String JSON_STORE = "./data/movielist.json";
    private Scanner input;
    private MovieList movieList;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;

    // EFFECTS: runs the movie tracking application
    public MovieTrackingAppConsole() throws FileNotFoundException {
        runMovieList();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMovieList() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }

        System.out.println("Successfully quit the application");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("a")) {
            doAdd();
        } else if (command.equals("r")) {
            doRemove();
        } else if (command.equals("v")) {
            doView();
        } else if (command.equals("m")) {
            doMark();
        } else if (command.equals("s")) {
            saveMovieList();
        } else if (command.equals("l")) {
            loadMovieList();
        } else {
            System.out.println("Selection not valid. Please select again.");
        }
    }

    // MODIFIES: this
    // EFFECTS: initializes the movie list
    private void init() {
        movieList = new MovieList();
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> add a movie to the list");
        System.out.println("\tr -> remove a movie from the list");
        System.out.println("\tv -> view the movie list");
        System.out.println("\tm -> mark a movie as watched");
        System.out.println("\ts -> save movie list to file");
        System.out.println("\tl -> load movie list from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: adds the movie with given name and status (watched or not) to the movie list
    private void doAdd() {
        System.out.println("Enter the name of the movie you want to add:");
        String name = input.next();
        System.out.println("Have you watched this movie? y = yes, n = no");
        String status = input.next();
        status = status.toLowerCase();
        while (!(status.equals("y") || status.equals("n"))) {
            System.out.println("Selection not valid. Please enter again: y = yes, n = no");
            status = input.next();
            status = status.toLowerCase();
        }

        if (status.equals("y")) {
            Movie movie = new Movie(name, true);
            movieList.addMovie(movie);
        } else {
            Movie movie = new Movie(name, false);
            movieList.addMovie(movie);
        }
        System.out.println("Successfully added the movie " + "\"" + name + "\"");
    }

    // MODIFIES: this
    // EFFECTS: removes the movie with given name from the movie list;
    //          if the list does not contain a movie with given name, notifies the user
    private void doRemove() {
        System.out.println("Enter the name of the movie you want to remove:");
        String name = input.next();
        int initialSize = movieList.sizeMovieList();
        movieList.removeMovieIf(name);
        if (movieList.sizeMovieList() == initialSize) {
            System.out.println("Your list does not contain " + "\"" + name + "\"");
        } else {
            System.out.println("Successfully removed the movie " + "\"" + name + "\"");
        }
    }


    // EFFECTS: prints the list of movies with name and status (watched or not) on the screen;
    //          if the list is empty, notifies the user
    private void doView() {

        if (movieList.sizeMovieList() == 0) {
            System.out.println("Your movie list is empty");
        } else {
            System.out.println("YOUR MOVIE LIST (Y = watched, N = not watched):");
            for (Movie movie : movieList.getMovies()) {
                System.out.println(movie.getName() + "   " + movie.getIsWatchedYN());
            }
        }

    }

    // MODIFIES: this
    // EFFECTS: marks the movie with given name as watched;
    //          if this movie is already marked as watched, notifies the user;
    //          if the list does not contain this movie, notifies the user
    private void doMark() {
        System.out.println("Enter the name of the movie you want to mark as watched:");
        String name = input.next();
        List<Movie> markedList = new ArrayList<>();
        for (Movie movie : movieList.getMovies()) {
            if (movie.getName().equals(name)) {
                if (movie.getIsWatched()) {
                    System.out.println("The movie " + "\"" + name + "\"" + " is already marked as watched");
                    markedList.add(movie);
                } else {
                    movie.setIsWatched((true));
                    System.out.println("Successfully marked " + "\"" + name + "\"" + " as watched");
                    markedList.add(movie);
                }
            }
        }

        if (markedList.size() == 0) {
            System.out.println("Your list does not contain " + "\"" + name + "\"");
        }
    }

    // EFFECTS: saves the movie list to file
    private void saveMovieList() {
        try {
            jsonWriter.open();
            jsonWriter.write(movieList);
            jsonWriter.close();
            System.out.println("Saved your movie list to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads movie list from file
    private void loadMovieList() {
        try {
            movieList = jsonReader.read();
            System.out.println("Loaded your movie list from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }
}





