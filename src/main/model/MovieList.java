package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.List;


// Represents a movie list containing a list of movies
public class MovieList implements Writable {
    private List<Movie> movies;

    // EFFECTS: constructs an empty movie list
    public MovieList() {
        this.movies = new ArrayList<>();
    }

    // MODIFIES: this
    // EFFECTS: adds given movie to the movie list; logs the corresponding event
    public void addMovie(Movie movie) {
        EventLog.getInstance().logEvent(new Event("Added movie: " + "\"" + movie.getName() + "\""));
        this.movies.add(movie);
    }

    // EFFECTS: returns the size of the movie list (an integer)
    public int sizeMovieList() {
        return this.movies.size();
    }

    // MODIFIES: this
    // EFFECTS: removes the movie with the given name from the list
    public void removeMovieIf(String name) {
        this.movies.removeIf(m -> m.getName().equals(name));
    }

    // MODIFIES: this
    // EFFECTS: removes movie of given index from the movie list; logs the corresponding event
    public void removeMovieAt(int index)  {
        EventLog.getInstance().logEvent(new Event("Removed movie: " + "\"" + movies.get(index).getName() + "\""));
        this.movies.remove(index);
    }

    // EFFECTS: return the movie list as a list
    public List<Movie> getMovies() {
        return this.movies;
    }

    @Override
    // EFFECTS: makes a new json object as needed and returns it
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("movies", moviesToJson());
        return json;
    }

    // EFFECTS: returns movies in this movie list as a JSON array
    private JSONArray moviesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Movie movie : movies) {
            jsonArray.put(movie.toJson());
        }

        return jsonArray;
    }

}
